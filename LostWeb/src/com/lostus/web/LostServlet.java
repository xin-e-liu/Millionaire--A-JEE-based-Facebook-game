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

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.facebook.api.*;
import com.facebook.api.schema.FriendsGetAppUsersResponse;
import com.lostus.ejb.record.Record;
import com.lostus.session.LostSessionRemote;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class LostServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static LostHolderCache holderCache = null;
    private static String apiKey = null;
    private static String secretKey = null;
    private static String requestURL = null;
    private static String FBCallbackURL = null;
    private String sessionKey = null;
    private String isRefresh = null;
    private int isRefreshFlag;
    private int pageIndex;

    // Facebook Rest Client
    FacebookRestClient facebookRestClient = null;

    /**
     * Initialization of the servlet. <br>
     * 
     * @throws ServletException
     *             if an error occurs
     */
    public void init() throws ServletException {

        apiKey = getServletConfig().getInitParameter("API_KEY");
        secretKey = getServletConfig().getInitParameter("SECRET_KEY");
        requestURL = getServletConfig().getInitParameter("REQUEST_URL");
        FBCallbackURL = getServletConfig().getInitParameter("FBCALLBACK_URL");

        long timeToLive = 0;
        holderCache = new LostHolderCache(timeToLive);
    }

    /**
     * Constructor of the object.
     */
    public LostServlet() {
        super();
    }

    /**
     * Destruction of the servlet. <br>
     */
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    /**
     * The doGet method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to get.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In main servlet");
        // Get current page
        String page = request.getParameter("page");
        if (page == null) {
            pageIndex = 1;
        } else if (page.equalsIgnoreCase("main")) {
            pageIndex = 1;
        } else if (page.equalsIgnoreCase("banking")) {
            pageIndex = 2;
        } else if (page.equalsIgnoreCase("auction")) {
            pageIndex = 3;
        } else if (page.equalsIgnoreCase("status")) {
            pageIndex = 4;
        } else if (page.equalsIgnoreCase("tab")) {

            // request from profile tab. redirect to tab page
            request.setAttribute("requestURL", requestURL);
            request.getRequestDispatcher("LostTab.jsp").forward(
                    request,
                    response);
            return;
        }

        // Check if the user has session ID
        sessionKey = request.getParameter("fb_sig_session_key");
        if (sessionKey == null) {
            String url = "http://www.facebook.com/login.php?api_key=" + apiKey
                    + "&v=1.0&canvas";
            System.out.println(url);
            response.getWriter().println("<fb:redirect url=\"" + url + "\" />");
            return;
        }

        // Create holder to handle all the requests
        LostHolder holder = null;
        try {
            holder = createHolder(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the page load is refresh
        isRefresh = request.getParameter("isRefresh");
        String preIsRefresh = holder.getLsr().getGS().getIsRefresh();
        if (isRefresh == null) {
            isRefreshFlag = 0;
        } else {
            if (isRefresh.equalsIgnoreCase(preIsRefresh)) {
                isRefreshFlag = 1;
            } else {
                isRefreshFlag = 0;
            }
            holder.getLsr().getGS().setIsRefresh(isRefresh);
        }

        // Prepare http header and content to be sent to
        // session bean for processing.
        Map<String, String> params = getRequestParameters(request);
        Map<String, String> headers = getRequestHeaders(request);

        final int contentLength = request.getContentLength();
        String inMessage = "";
        if (contentLength > 1) {
            byte[] requestData = new byte[contentLength];
            InputStream in = null;
            try {
                in = request.getInputStream();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int n = 0;
            int bytesRead = 0;

            try {
                do {
                    n = in.read(requestData, bytesRead, requestData.length
                            - bytesRead);
                    if (n > 0) {
                        bytesRead += n;
                    }
                } while (n != -1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            inMessage = new String(requestData);

            /*
             * try { FileOutputStream outPutFile = new FileOutputStream(
             * "/receivedPackage.xml", true); DataOutputStream dataOutPut = new
             * DataOutputStream(outPutFile); dataOutPut.writeBytes(inMessage);
             * dataOutPut.close(); } catch (Exception e) { e.printStackTrace();
             * }
             */
        }

        System.out.println(params.toString());

        // Get user information using facebook api
        String name = "";
        String picture = "";
        try {
            long myId = holder.getFacebookRestClient().users_getLoggedInUser();

            EnumSet<ProfileField> fields = EnumSet.of(
                    com.facebook.api.ProfileField.NAME,
                    com.facebook.api.ProfileField.PIC);

            Collection<Long> users = new ArrayList<Long>();
            users.add(myId);

            Document d = holder.getFacebookRestClient().users_getInfo(
                    users,
                    fields);
            name = d.getElementsByTagName("name").item(0).getTextContent();
            picture = d.getElementsByTagName("pic").item(0).getTextContent();

            request.setAttribute("picture", picture);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Check if the user is a new user. If so, add the user into database.
        if (holder.getNeedCheck() == 1) {
            System.out.println("We need to check if the user is new or not");
            if (holder.getLsr().isUserExist(holder.getUserId()) == 0) {
                System.out.println("User is completely new");
                holder.getLsr().addNewUser(holder.getUserId(), name);
                holder.getLsr()
                        .setUserSessionId(holder.getUserId(), sessionKey);

            } else {
                String preSessionId = holder.getLsr().getSessionIdByUserId(
                        holder.getUserId());
                if (!preSessionId.equalsIgnoreCase(sessionKey)) {
                    System.out
                            .println("User NOT new, but session id from facebook expires");
                    holder.getLsr().setUserSessionId(
                            holder.getUserId(),
                            sessionKey);
                }
            }
            holder.setIsNewUser(1);
            holder.getLsr().getGS().setIsNewUser(1);
        } else {
            holder.setIsNewUser(0);
            holder.getLsr().getGS().setIsNewUser(0);
        }

        // Process the request
        if (isRefreshFlag == 0) {
            holder.getLsr().processRequest(params);
        }

        // JSP page for presentation
        request.setAttribute("LostHolder", holder);

        if (pageIndex == 1) {
            if (holder.getLsr().getGS().getIsGameOver() == 1) {
                request.getRequestDispatcher("GameOver.jsp").forward(
                        request,
                        response);
            } else if (holder.getLsr().getGS().getIsNewUser() == 1) {
                request.getRequestDispatcher("invite.jsp").forward(
                        request,
                        response);
            } else if (holder.getLsr().getGS().getSendEmail() == 1) {
                startSendEmail();
            } else {

                request.getRequestDispatcher("LostMain.jsp").forward(
                        request,
                        response);
            }
        } else if (pageIndex == 2) {
            request.getRequestDispatcher("LostBanking.jsp").forward(
                    request,
                    response);
        } else if (pageIndex == 3) {
            request.getRequestDispatcher("LostAuction.jsp").forward(
                    request,
                    response);
        } else if (pageIndex == 4) {
            request.getRequestDispatcher("LostStatus.jsp").forward(
                    request,
                    response);
        }

        // Reset the values
        holder.setNeedCheck(0);
        holder.setIsNewUser(0);
        holder.getLsr().getGS().setIsNewUser(0);
        holder.getLsr().getGS().setIsGameOver(0);
        holder.getLsr().getGS().setSendEmail(0);

    }

    /**
     * The doPost method of the servlet. <br>
     * 
     * This method is called when a form has its tag value method equals to
     * post.
     * 
     * @param request
     *            the request send by the client to the server
     * @param response
     *            the response send by the server to the client
     * @throws ServletException
     *             if an error occurred
     * @throws IOException
     *             if an error occurred
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

    private LostHolder createHolder(HttpServletRequest request)
            throws Exception {

        String sessionKey = request.getParameter("fb_sig_session_key");
        int inComingUserId = new Integer(request.getParameter("fb_sig_user"));

        LostHolder holder = null;
        holder = (LostHolder) holderCache.get(sessionKey);

        if (holder == null) {

            holder = (LostHolder) Class.forName("com.lostus.web.LostHolder")
                    .newInstance();

            // Since there is no holder for the request, need to check whether
            // this user is a new one
            holder.setNeedCheck(1);

            holder.setSessionId(sessionKey);
            holder.setApiKey(apiKey);
            holder.setSecretKey(secretKey);
            holder.createLsr(1);
            holder.setUserId(inComingUserId);
            holder.getLsr().getGS().setUserId(inComingUserId);
            // Create Facebook Rest Client
            facebookRestClient = new FacebookRestClient(apiKey, secretKey,
                    sessionKey);
            holder.setFacebookRestClient(facebookRestClient);

            // Set URL parameters
            holder.setRequestURL(requestURL);
            holder.setFBCallbackURL(FBCallbackURL);

            holderCache.put(holder);
        }
        holder.setLsr(1);
        return holder;
    }

    private Map getRequestHeaders(HttpServletRequest request) {
        Map headers = new HashMap();

        String headerName = null;
        for (Enumeration e = request.getHeaderNames(); e.hasMoreElements();) {
            headerName = (String) e.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }

        return headers;
    }

    private Map<String, String> getRequestParameters(HttpServletRequest request) {
        Map<String, String> params = new HashMap();

        String paramName = null;
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
            paramName = (String) e.nextElement();
            params.put(paramName, request.getParameter(paramName));
        }

        return params;
    }

    private final static long fONCE_PER_DAY = 1000 * 10;

    private final static int fONE_DAY = 1;
    private final static int fFOUR_AM = 4;
    private final static int fZERO_MINUTES = 0;

    private static Date getTomorrowMorning4am() {
        Calendar tomorrow = new GregorianCalendar();
        tomorrow.add(Calendar.DATE, fONE_DAY);
        Calendar result = new GregorianCalendar(tomorrow.get(Calendar.YEAR),
                tomorrow.get(Calendar.MONTH), tomorrow.get(Calendar.DATE),
                fFOUR_AM, fZERO_MINUTES);
        return result.getTime();
    }

    public void startSendEmailTimer() {
        SendEmail se = new SendEmail(apiKey, secretKey, sessionKey,
                FBCallbackURL);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(se, 1000, fONCE_PER_DAY);

    }

    public void startSendEmail() {

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

                email.append("Current No.1 Cash Maker is " + topUserName
                        + " with total cash " + topUserCash + ".\n");
                if (flag == 0) {
                    email.append("Your current highest rank is "
                            + currentFriendRank
                            + ". Sorry you need a lot of work to keep up. \n"
                            + "Come and beat the record here: " + FBCallbackURL);
                } else {
                    email.append("Your current highest rank is "
                            + currentFriendRank + " with total cash "
                            + currentFriendCash
                            + "\n. Come and beat the record here: "
                            + FBCallbackURL);
                }

                List<Long> l = new ArrayList<Long>();
                l.add(availFriends.get(t));

                client.notifications_sendTextEmail(
                        l,
                        "Millionaire News",
                        email.toString());
                // System.out.println(email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
