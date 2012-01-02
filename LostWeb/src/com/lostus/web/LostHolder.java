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

import java.io.Serializable;
import javax.naming.*;
import java.util.Map;

//import com.facebook.api.FacebookXmlRestClient;
import com.facebook.api.*;
import com.lostus.session.*;

public class LostHolder implements Serializable {

    private static final long serialVersionUID = 1L;
    private String sessionId;
    private String value;
    private LostSessionRemote lsr = null;

    private String api_key = null;

    private String secret_key = null;

    private long creationTimestamp;

    private int userId;

    private int isNewUser;

    private FacebookRestClient facebookRestClient;

    private String requestURL;

    private String FBCallbackURL;

    private int needCheck;

    public void LostHolder() {
        try {
            InitialContext ctx = new InitialContext();
            lsr = (LostSessionRemote) ctx
                    .lookup("com.lostus.session.LostSessionRemote");

        } catch (Exception e) {
            e.printStackTrace();
        }
        lsr.getDBConn();
        creationTimestamp = System.currentTimeMillis();
    }

    public void LostHolder(String sessionId, String value, int data) {
        this.sessionId = sessionId;
        this.value = value;
        try {
            InitialContext ctx = new InitialContext();
            lsr = (LostSessionRemote) ctx
                    .lookup("com.lostus.session.LostSessionRemote");

        } catch (Exception e) {
            e.printStackTrace();
        }

        lsr.setData(lsr.getData() + data);
        lsr.getDBConn();

        creationTimestamp = System.currentTimeMillis();

    }

    public void setSecretKey(String secret_key) {
        this.secret_key = secret_key;
    }

    public String getSecretKey() {
        return this.secret_key;
    }

    public void setApiKey(String api_key) {
        this.api_key = api_key;
    }

    public String getApiKey() {
        return this.api_key;
    }

    public void setNeedCheck(int needCheck) {
        this.needCheck = needCheck;
    }

    public int getNeedCheck() {
        return this.needCheck;
    }

    public String getRequestURL() {
        return this.requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public String getFBCallbackURL() {
        return this.FBCallbackURL;
    }

    public void setFBCallbackURL(String FBCallbackURL) {
        this.FBCallbackURL = FBCallbackURL;
    }

    public int getIsNewUser() {
        return this.isNewUser;
    }

    public void setIsNewUser(int isNewUser) {
        this.isNewUser = isNewUser;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getValue() {
        return value;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LostSessionRemote getLsr() {
        // System.out.println(this.lsr.getData());
        return this.lsr;
    }

    public void setLsr(int data) {
        this.lsr.setData(this.lsr.getData() + data);

    }

    public void createLsr(int data) {
        LostHolder();
        this.lsr.setData(data);

    }

    public String getRespMsg(Map params, Map headers, String inMsg) {
        return this.lsr.getRespMsg(params, headers, inMsg);

    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void close() throws Exception {
        // Do cleaning up work when the holder expires.
    }

    public void setFacebookRestClient(FacebookRestClient facebookRestClient) {
        this.facebookRestClient = facebookRestClient;
    }

    public FacebookRestClient getFacebookRestClient() {
        return this.facebookRestClient;
    }

}
