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

package com.lostus.session;

public class GamingState {

    private static final long serialVersionUID = 1L; 

    private String isRefresh;

    private int userId;

    private int locationId;

    private int ttl;

    private int isNewUser;

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return this.locationId;
    }

    public void setTTL(int ttl) {
        this.ttl = ttl;
    }

    public int getTTL() {
        return this.ttl;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public int getIsNewUser() {
        return this.isNewUser;
    }

    public String getIsRefresh() {
        return this.isRefresh;
    }

    public void setIsRefresh(String isRefresh) {
        this.isRefresh = isRefresh;
    }

}
