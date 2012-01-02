/*
Copyright 2007 codeoedoc

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.lostus.web;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

import java.util.Iterator;

import java.io.Serializable;

public class LostHolderCache implements Serializable {
    // --------------------------------------------------------------- Constants

    public static final long CLEANING_PERIOD = 3000000; // 50 mins
    public static final long DEFAULT_TTL = 3000000; // 50 mins

    private long holderTimeToLive = DEFAULT_TTL;

    private long lastCleaningTimestamp = 0;

    private HashMap cache;

    public LostHolderCache(long timeToLive) {
        cache = new HashMap(97);

        lastCleaningTimestamp = System.currentTimeMillis();

        if (timeToLive == 0) {
            timeToLive = DEFAULT_TTL;
        }
        holderTimeToLive = timeToLive;
    }

    // ------------------------------------------- Implementation of HolderCache

    public void put(LostHolder holder) {

        // Before putting a new holder in the cache, check if there are old
        // holders to be removed.
        clean();

        cache.put(holder.getSessionId(), holder);
    }

    public LostHolder get(String sessionId) {
        return (LostHolder) cache.get(sessionId);
    }

    public void remove(String sessionId) {
        cache.remove(sessionId);
    }

    // ---------------------------------------------------- Other public methods

    public String toString() {
        return String.valueOf(cache);
    }

    // --------------------------------------------------------- Private methods

    /**
     * If System.currentTimeMillis() - lastCleaningTimestamp is greater than
     * holderTimeToLive, purge old holders.
     * 
     * @returns true if cleaning was performed, false otherwise
     */
    private boolean clean() {
        long now = System.currentTimeMillis();

        if ((now - lastCleaningTimestamp) <= CLEANING_PERIOD) {
            return false;
        }
        ArrayList toBeRemoved = new ArrayList();

        LostHolder h;
        Object key, value;
        Iterator i = cache.keySet().iterator();
        while (i.hasNext()) {
            key = i.next();
            value = cache.get(key);

            if (!(value instanceof LostHolder)) {
                // You shouldn't be here matey!!!
                toBeRemoved.add(key);
                continue;
            }
            h = (LostHolder) value;
            if ((now - h.getCreationTimestamp()) > holderTimeToLive) {
                try {
                    h.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                toBeRemoved.add(key);
            }
        }
        //
        // Now remove the purged objects
        //
        i = toBeRemoved.iterator();
        while (i.hasNext()) {
            cache.remove(i.next());
        }

        return true;
    }

}
