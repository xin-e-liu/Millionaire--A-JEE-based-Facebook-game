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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.lostus.web.*"%>
<%@ page import="com.lostus.session.*"%>
<%@ page import="com.lostus.ejb.user.*"%>
<%@ page import="com.lostus.ejb.shopping.*"%>
<%@ page import="com.lostus.ejb.usercart.*"%>
<%@ page import="com.lostus.ejb.location.*"%>
<%@ page import="com.lostus.ejb.event.*"%>
<%@ page import="com.lostus.ejb.news.*"%>
<%@ page import="com.lostus.ejb.record.*"%>
<%@ page import="com.facebook.api.*"%>
<%@ page import="org.w3c.dom.Document"%>

<%
	LostHolder holder = null;
	holder = (LostHolder) request.getAttribute("LostHolder");
	int userId = holder.getUserId();
	String fBCallbackURL = holder.getFBCallbackURL();
	String bkURL = holder.getRequestURL() + "figures/bkg/";

	List<Record> recordList = holder.getLsr().getSortedRecordGS();
	List<String> tmpPicList = new ArrayList();
	List<String> picList = new ArrayList();

	List<Long> users = new ArrayList<Long>();

	FacebookRestClient client = holder.getFacebookRestClient();

	EnumSet<ProfileField> fields = EnumSet.of(
			com.facebook.api.ProfileField.NAME,
			com.facebook.api.ProfileField.PIC);
	Document d = null;

	String backgroundImgURL = bkURL + "gameover.jpg";
	String photoSubstitute = bkURL + "smile.jpg";

	int position = 0;
	Record selfRecord=null;
	for (int t = recordList.size() - 1; t >= 0; t--) {
		position++;
		selfRecord = (Record) recordList.get(t);
		if (selfRecord.getUid() == userId)
			break;
	}
%>

<table width="100%" id="table_yourrecord" border="1" bgcolor="yellow">
	<tr>
		<td>
			<font size="5" face="Times">
			Your Highest Rank: 
			</font>
			
			<font size="5" face="Times" color="red">
			<%=position %> 
			</font>
			
			<font size="5" face="Times">
			Cash: 
			</font>
			
			<font size="5" face="Times" color="red">
			$ <%=selfRecord.getUcash()%>
			</font>
			
		</td>
	</tr>
</table>

<legend>
	<font size="3" face="Times"> Top 20 Records </font>
</legend>
<table width="100%" id="table_recordList" border="1"
	background=<%=backgroundImgURL%>>
	<tr>
		<td style="width: 20%" nowrap>
		</td>
		<td style="width: 30%" nowrap>
			Player
		</td>
		<td style="width: 20%" nowrap>
			Cash
		</td>
		<td style="width: 30%" nowrap>
			Record Date
		</td>
	</tr>
	<%
		out
				.print("<input type=\"hidden\" id=\"callbackurl\" name=\"callbackurl\" ");
		out.print("value=\"" + fBCallbackURL + "\"" + "/>");

		Record record = null;
		String tmpPic = "";
		String tmpLink = "";

		int count=0;
		for (int t = recordList.size() - 1; t >= 0; t--) {
			count++;
			
			users.clear();

			if (t == recordList.size() - 21) {
				break;
			}

			record = (Record) recordList.get(t);

			users.add(Long.valueOf(Integer.toString(record.getUid())));
			d = client.users_getInfo(users, fields);

			if (d.getElementsByTagName("pic").getLength() != 0) {
				tmpPic = d.getElementsByTagName("pic").item(0)
						.getTextContent();
			} else {
				tmpPic = "";
			}

			if (tmpPic.equalsIgnoreCase("")) {
				tmpPic = photoSubstitute;
			}

			tmpLink = "http://www.facebook.com/profile.php" + "?id="
					+ record.getUid();
			//row
			out.println("<tr>");
			out.println("<td>");
			out.print("<a href=\"" + tmpLink
					+ "\" target=\"_blank\"> <img src=\"" + tmpPic + "\""
					+ " width=\"50\" height=\"50\"/> </a>");
			out.print("   ");
			out.print("<font size=\"5\" color=\"purple\">");
			out.print(count);
			out.print("</font>");
			out.println("</td>");

			//First column
			if (record.getUid() == userId) {
				out
						.println("<td style=\"color:red; font-size:10pt; font-weight:bold; font-family:'Sans-serif'\" >");
			} else {
				out
						.println("<td style=\"color:blue; font-size:10pt; font-weight:bold; font-family:'Sans-serif'\" >");
			}
			out.println(record.getUname());
			out.println("</td>");

			//Second column
			out.println("<td nowrap >");
			out.println(record.getUcash());
			out.println("</td>");

			//Third column
			out.println("<td nowrap >");
			out.println(record.getRtimestamp());
			out.println("</td>");

		}
	%>

	<tr>
		<td colspan="3" align="middle">
			<input type="button" id="restart" name="restart"
				value='Play Again! ' onClick="RestartGame();"
				style="width: 5cm" />
		</td>
	</tr>
</table>






<script type="text/javascript">

//User restart the game
function RestartGame(){
var cURL = document.getElementById("callbackurl").getValue();
document.setLocation(cURL+"?userAction=restart");
}


</script>


