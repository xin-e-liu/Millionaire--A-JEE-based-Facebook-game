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

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

import javax.naming.InitialContext;

import org.w3c.dom.Document;

import com.facebook.api.FacebookRestClient;
import com.facebook.api.ProfileField;
import com.facebook.api.schema.FriendsGetAppUsersResponse;
import com.lostus.ejb.record.Record;
import com.lostus.session.LostSessionRemote;

public class SendEmail extends TimerTask {

    private String apiKey = null;
    private String secretKey = null;
    private String FBCallbackURL = null;
    private String sessionKey = null;

    public SendEmail(String apiKey, String secretKey, String sessionKey,
            String cbURL) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.sessionKey = sessionKey;
        this.FBCallbackURL = cbURL;
    }

    public void run() {

        LostSessionRemote lsr = null;
        FacebookRestClient client = new FacebookRestClient(apiKey, secretKey,
                sessionKey);

        try {
            InitialContext ctx = new InitialContext();
            lsr = (LostSessionRemote) ctx
                    .lookup("com.lostus.session.LostSessionRemote");

        } catch (Exception e) {
            e.printStackTrace();
        }
        lsr.getDBConn();

        lsr.getGS().setRecordList(lsr.getAllRecord());

        List<com.lostus.ejb.record.Record> recordList = lsr.getSortedRecordGS();

        int topUserId = recordList.get(recordList.size() - 1).getUid();
        int topUserCash = recordList.get(recordList.size() - 1).getUcash();

        // List<com.lostus.ejb.user.User> aul=lsr.getAllUser();

        client.setIsDesktop(false);

        EnumSet<ProfileField> fields = EnumSet.of(
                com.facebook.api.ProfileField.NAME,
                com.facebook.api.ProfileField.PIC);

        Collection<Long> users = new ArrayList<Long>();
        users.add(Long.valueOf(Integer.toString(topUserId)));

        Document d = null;
        try {
            d = client.users_getInfo(users, fields);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String topUserName = d.getElementsByTagName("name").item(0)
                .getTextContent();
        String picture = d.getElementsByTagName("pic").item(0).getTextContent();

        List<Long> availFriends = new ArrayList<Long>();
        try {
            client.friends_getAppUsers();
            FriendsGetAppUsersResponse friendAppUsersResp = (FriendsGetAppUsersResponse) client
                    .getResponsePOJO();
            availFriends = friendAppUsersResp.getUid();

            for (int t = 0; t < availFriends.size(); t++) {

                int friendID = availFriends.get(t).intValue();
                System.out.println(friendID);

                Record record = null;
                int currentFriendRank = 0;
                int currentFriendCash = 0;
                int flag = 0;
                for (int tt = recordList.size() - 1; tt >= 0; tt--) {
                    record = (Record) recordList.get(tt);
                    if (record.getUid() == friendID) {
                        currentFriendRank = recordList.size() - tt;
                        currentFriendCash = record.getUcash();
                        flag = 1;
                        break;
                    }
                }

                StringBuffer email = new StringBuffer();
                email.append("<html>");

                email.append("Current No.1 Cash Maker is " + topUserName
                        + " with total cash " + topUserCash + ".\n");
                if (flag == 0) {
                    email.append("Your current highest rank is "
                            + currentFriendRank
                            + ". Sorry you need a lot of work to keep up. <a href=\""
                            + FBCallbackURL + "\" >Come and beat </a>"
                            + topUserName);
                } else {
                    email.append("Your current highest rank is "
                            + currentFriendRank + " with total cash "
                            + currentFriendCash + ". Come and beat "
                            + topUserName);
                }
                email.append("</html>");
                // client.notifications_sendTextEmail(availFriends,
                // "Millionaire News", email.toString());
                System.out.println(email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
