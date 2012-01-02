<!--
 Copyright 2011 codeoedoc

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
-->

<%@page import="com.facebook.api.schema.*" import="com.facebook.api.*"
	import="java.util.List" import="java.util.ArrayList"%>
<%@ page import="com.lostus.web.*"%>
<%@ page import="org.w3c.dom.Document"%>
<%@ page import="org.w3c.dom.NodeList"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	LostHolder holder = null;
	holder = (LostHolder) request.getAttribute("LostHolder");
	
	String fBCallbackURL=holder.getFBCallbackURL();
	
	String bkURL = holder.getRequestURL() + "figures/bkg/";
	
	String api_key=holder.getApiKey();

	FacebookRestClient client = holder.getFacebookRestClient();
	client.setIsDesktop(false);
	
	//client.notifications_sendTextEmailToCurrentUser("abc", "edf");

	List<Long> availFriends = new ArrayList<Long>();
	try {
		client.friends_getAppUsers();
		FriendsGetAppUsersResponse friendAppUsersResp=(FriendsGetAppUsersResponse)client.getResponsePOJO();
		availFriends=friendAppUsersResp.getUid();

	} catch (Exception e) {
		System.out.println(e.toString());
	}
%>

<p style="font-family:verdana;font-size:200%;color:green;text-align:center">
Want the feeling of being a BILLIONAIRE?
</p>
<p style="font-family:courier;font-size:150%;color:black;text-align:center">
Want to show your profit skills?
</p>
<p style="font-family:times;font-size:180%;color:blue;text-align:center">
Want to be TOP of your friends?
</p>
<p style="font-family:Comic Sans MS;font-size:200%;color:red;text-align:center">
Come and Join!!!
</p>


<table style="width: 15cm" align="center">
<tr>
<td style="width: 8cm" align="center">
<img src="<%=bkURL+"invite1.jpg" %>" alt="Angry" width="280" height="250" />
</td>
<td style="width: 7cm" align="center">
<img src="<%=bkURL+"invite2.jpg" %>" alt="Angry" width="260" height="260" />
</td>
</tr>
<tr>
<td colspan="2">
<font color="blue">To do:</font> walk around <font color="red">"land of gold"</font>, click on the stuff you would like to buy, 
input the <font color="red">"quantity"</font> and press <font color="red">"buy"</font>. When selling something, click on the stuff 
you want to sell, enter the <font color="red">"quantity"</font> and press <font color="red">"sell"</font>. Initially you have $2000 
to start and 100 days to become a billionaire. Each move reduces your time, 
but brings new opportunities. Your cart at most holds 100 items. So don't 
get too full with the cheap stuff. Enjoy!
</td>
</tr>
</table>

<c:set var="invite">
  Hey, come and add this application to be Billionaire with me! Challenge me. See who is the real one!
  &lt;fb:req-choice url='http://www.facebook.com/add.php?api_key=<%=api_key%>' label='Be A Billionaire' /&gt;
</c:set>

<fb:request-form action="<%=fBCallbackURL %>"
	method="POST" invite="true" type="Millionaire" content="${invite}">

	<fb:multi-friend-selector showborder="false"
		actiontext="Invite friends to be Billionaire."
		exclude_ids="
		<%
		
		for(int t=0; t<availFriends.size(); t++){
		out.print(availFriends.get(t));
		out.print(",");
		}
		%>" max="20" />

</fb:request-form>


