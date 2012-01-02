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

<%@ page import="java.util.Random"%>

<%
	//The load game state
	LostHolder holder = null;
	int userId = 0;
	int userCash = 0;
	int userDeposit = 0;
	int userDebt = 0;
	int userHealth = 0;
	int userReputation = 0;
	int userTTL = 0;
	int userCapacity = 0;

	holder = (LostHolder) request.getAttribute("LostHolder");
	String picture = (String) request.getAttribute("picture");
	userId = holder.getUserId();

	//Get the request URL
	String bkURL = holder.getRequestURL() + "figures/bkg/";
	String ads1URL = holder.getRequestURL() + "figures/ads1/";
	String ads2URL = holder.getRequestURL() + "figures/ads2/";
	String fBCallbackURL = holder.getFBCallbackURL();
	String requestURL = holder.getRequestURL();
	String tmpURL = requestURL + "index.jsp";
	String warehousBackgroundImageURL=bkURL + "warehouse.gif";

	Random generator = new Random(System.nanoTime());
	int r = generator.nextInt();
	String jsURL = holder.getRequestURL() + "LostMainJS.js?" + r;

	int locationId = holder.getLsr().getGS().getLocationId();

	List<Shopping> commodityList = holder.getLsr()
			.getMarketListByuIdlIdGS(userId, locationId);

	com.lostus.ejb.user.User u = holder.getLsr().getGS().getUser();
	userCash = u.getUmoney();
	userDeposit = u.getUdeposit();
	userDebt = u.getUdebt();
	userHealth = u.getUhealth();
	userReputation = u.getUreputation();
	userTTL = u.getUttl();
	userCapacity = u.getUcapacity();

	//Initialize 
	List<com.lostus.ejb.location.Location> locationList = holder
			.getLsr().getLocationByTypeGS(0);
	List<UserCart> cartList = holder.getLsr().getCartListGS(userId);
	List<News> newsList = holder.getLsr().getNewsListByUidGS(userId);

	com.lostus.ejb.location.Location location = (com.lostus.ejb.location.Location) locationList
			.get(locationId - 13);
	String backgroundImageURL = bkURL + location.getLid() + ".jpg";

	FacebookRestClient client = holder.getFacebookRestClient();
	
	String mainPageURL=fBCallbackURL+"?page=main";
	String bankingPageURL=fBCallbackURL+"?page=banking";
	String auctionPageURL = fBCallbackURL + "?page=auction";
	String statusPageURL = fBCallbackURL + "?page=status";
	
%>


<fb:tabs>
	<fb:tab-item href=<%=mainPageURL %>
		title='Main' selected='true' />
	<!--
	<fb:tab-item href=<%=bankingPageURL %>
		title='Trade' />
	  <fb:tab-item href=<%=auctionPageURL %>
		title='Auction' /> 
	<fb:tab-item href=<%=statusPageURL %>
		title='Status' />
		-->
</fb:tabs>


<table width="100%" border="0" background=<%=backgroundImageURL%>>

	<tr>
		<td>

			<table width="100%" border="0">
				<tr>
					<td width="45%">
						<fieldset>
							<legend>
								Black Market
							</legend>
							<table id="table_CommodityListId" width="100%">
								<%
									Shopping shopItem = null;
									for (int t = 0; t < commodityList.size(); t++) {
										shopItem = (Shopping) commodityList.get(t);

										//row
										out.println("<tr>");

										//First column
										out
												.println("<td style=\"color:red; font-size:10pt; font-weight:bold; font-family:'Sans-serif'\">");
										out.println(holder.getLsr().getCNameByCIdGS(
												shopItem.getId().getCid()));
										out
												.println("<input type=\"radio\" id=\"radio_CommodityListId\" name=\"radio_CommodityList\" value=\""
														+ shopItem.getId().getCid()
														+ "_"
														+ shopItem.getCprice()
														+ "\" "
														+ "onClick=\"setBlackMarketRadioButtonValue();\""
														+ "/>");
										out.println("</td>");

										//Second column
										out.println("<td nowrap>");
										out.println("$" + shopItem.getCprice());
										out.println("</td>");

										//Third column
										out.println("<td nowrap>");
										out.print("<a href=\""
												+ holder.getLsr().getAdvertisingByIDGS(1,
														shopItem.getId().getCid(), 0).getAdlink()
												+ "\" target=\"_blank\"> <img src=\""
												+ ads1URL
												+ holder.getLsr().getAdvertisingByIDGS(1,
														shopItem.getId().getCid(), 0).getAdimage()
												+ "\" alt=\"Angry\" width=\"60\" height=\"50\"/> </a>");
										out.println("</td>");
										out.println("</tr>");

									}

									for (int t = 0; t < 5 - commodityList.size(); t++) {
										out.println("<tr>");
										out.println("<td>");
										out.println("&nbsp;");
										out.println("</td>");
										out.println("</tr>");
									}
								%>

							</table>
						</fieldset>
					</td>
					<td width="10%">
						<fieldset>
							<legend>
								Quantity
							</legend>
							<br />
							<form id="marketFormId" name="marketFormName"
								action=<%=fBCallbackURL%> method="post">

								<input type="text" id="quantityTextId" name="quantity" value='0'
									style="width: 100%" />

								<br />
								<br />
								<input type="button" id="buyButtonId" name="buyButtonName"
									value='BUY' onClick="buy();" style="width: 100%" />
								<br />

								<br />
								<input id="sellButtonId" name="sellButtonName" type="button"
									value="SELL" onClick="sell();" style="width: 100%" />


								<input type="hidden" id="blackMarketHiddenButtonId"
									name="buyInfo" value='' />
								<input type="hidden" id="cartListHiddenButtonId" name="sellInfo"
									value='' />

								<input type="hidden" id="currentLocationHiddenButtonId"
									name="location" value=<%=locationId%> />
								<input type="hidden" id="callBackURLHiddenButtonId"
									name="callBackURLHiddenButtonName" value=<%=fBCallbackURL%> />
								<input type="hidden" id="userIdHiddenButtonId"
									name="userIdHiddenButtonName" value=<%=userId%> />
								<input type="hidden" id="userCapacityHiddenButtonId"
									name="userCapacityHiddenButtonName" value=<%=userCapacity%> />
								<input type="hidden" id="requestURLHiddenButtonId"
									name="requestURLHiddenButtonName" value=<%=requestURL%> />

								<input type="hidden" id="userActionId" name="userAction"
									value='' />
								<input type="hidden" id="cQuantityBuyId" name="cQuantityBuy"
									value='' />
								<input type="hidden" id="cQuantitySellId" name="cQuantitySell"
									value='' />
									
									

								<input type="hidden" id="isRefreshId" name="isRefresh"/>
							</form>
						</fieldset>

					</td>
					<td width="45%">
						<fieldset>
							<legend>
								My warehouse(
								<%
								out.print("<span id=\"hasGoods\">");
								out.print(100 - userCapacity);
								out.print("</span>");
							%>
								<span id="roomtotal">/100</span>
								<input type="hidden" id="roomtotal_hide" name="roomtotal_hide"
									value="100" />
								)
							</legend>

							<div
								style="overflow-x: scroll; width: 100%; overflow: -moz-scrollbars-horizontal;">
								<select name="cartListName" size="10" id="cartListId"
									style="color:blue; width: 200%; background: 100% 100% no-repeat" onClick="setCartListValue();">
									<%
										UserCart uC = null;
										for (int t = 0; t < cartList.size(); t++) {
											uC = (UserCart) cartList.get(t);
											out.println("<option value=" + uC.getId().getCid() + "_"
													+ uC.getId().getPriceBought() + "_" + uC.getQuantity()
													+ ">");
											out.print(holder.getLsr().getCNameByCIdGS(uC.getId().getCid())
													+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
											out.print("$" + uC.getId().getPriceBought()
													+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
											out.print("Quantity: " + uC.getQuantity());
											out.println("</option>");
										}
									%>
								</select>
							</div>
						</fieldset>
					</td>
				</tr>

			</table>

			<table width="100%" border="0">

				<tr>
					<td width="30%">
						<fieldset>
							<legend>
								Your have survived:
								<%
								out.print("<span id=\"hmDay\">");
								out.print(100 - userTTL);
								out.print("</span>/100 days");
							%>
							</legend>

							<table width="100%" cellpadding="2" cellspacing="5">

								<tr>
									<td width="30%" height="30%" align="left" nowrap="nowrap">
										<font color="blue" size="2">
										Cash in pocket:
										</font>
									</td>
									<td rowspan="2">
										<img src=<%=picture%> width="100" height="100" />
									</td>
								</tr>
								<tr>
								<td colspan="2">
								<font color="red" size="2">
										$ <%=userCash%>
										</font>
										<input type="hidden" id="userCash" name="userCash"
											value="<% out.println(userCash); %>" />
									</td>
								</tr>
								
								<!--  
								<tr>
									<td height="30%" nowrap="nowrap">
										Deposit
									</td>
									<td colspan="3">
										<%
											out.println("<span id=\"deposit\">");
											out.println(userDeposit);
											out.println("</span>");
										%>
									</td>
								</tr>
								<tr>
									<td height="30%" nowrap="nowrap">
										Debt
									</td>

									<td colspan="3">
										<%
											out.println("<span id=\"debt\">");
											out.println(userDebt);
											out.println("</span>");
										%>
									</td>
								</tr>
								<tr>
									<td height="30%" nowrap="nowrap">
										Health
									</td>
									<td width="76%">
										<%
											out.println("<span id=\"health\">");
											out.println(userHealth);
											out.println("</span>");
										%>
									</td>
								</tr>
								<tr>
									<td>
										Reputation
									</td>
									<td width="76%">
										<%
											out.println("<span id=\"reputation\">");
											out.println(userReputation);
											out.println("</span>");
										%>
									</td>
								</tr>
								-->
								
							</table>


						</fieldset>
					</td>
					<td width="70%">
						<fieldset>
							<legend>
								NY Times for Billionaire
							</legend>

							<table id="table_NewsListId" style="width: 100%">
								<%
									News news = null;
									for (int t = 0; t < newsList.size(); t++) {
										news = (News) newsList.get(t);

										//row
										out.println("<tr>");

										//First column
										out.println("<td style=\"width: 80%\" >");
										out.println("* " + news.getContent());

										out.println("</td>");

										//Second column

										out.println("<td style=\"width: 20%\">");

										String linkAddr = holder.getLsr().getAdvertisingByIDGS(2,
												news.getIdInType(), news.getProperty()).getAdlink();
										String imgAddr = holder.getLsr().getAdvertisingByIDGS(2,
												news.getIdInType(), news.getProperty()).getAdimage();

										int idToShow = 0;
										if (news.getType().intValue() > 100) {
											idToShow = news.getType();

											if (imgAddr.equalsIgnoreCase("own")) {
												imgAddr = picture;

												out.print("<a href=\"" + linkAddr
														+ "\" target=\"_blank\"> <img src=\"" + imgAddr
														+ "\" width=\"50\" height=\"50\"/> </a>");

											} else {

												EnumSet<ProfileField> fields = EnumSet.of(
														com.facebook.api.ProfileField.NAME,
														com.facebook.api.ProfileField.PIC);

												Collection<Long> users = new ArrayList<Long>();
												users.add(Long.valueOf(Integer.toString(idToShow)));

												Document d = client.users_getInfo(users, fields);
												imgAddr = d.getElementsByTagName("pic").item(0)
														.getTextContent();

												linkAddr = linkAddr + "?id=" + idToShow;

												out.print("<a href=\"" + linkAddr
														+ "\" target=\"_blank\"> <img src=\"" + imgAddr
														+ "\" width=\"50\" height=\"50\"/> </a>");
											}

										} else {

											out.print("<a href=\"" + linkAddr
													+ "\" target=\"_blank\"> <img src=\"" + ads2URL
													+ imgAddr + "\" width=\"50\" height=\"50\"/> </a>");
										}

										out.println("</td>");

										out.println("</tr>");

									}

									if (newsList.size() == 0) {
										out.println("<tr>");
										out.println("<td>");
										out.println("Good day commander. No news is good news.");
										out.println("</td>");
										out.println("</tr>");
									}

									for (int t = 0; t < 5 - newsList.size(); t++) {
										out.println("<tr>");
										out.println("<td>");
										out.println("&nbsp;");
										out.println("</td>");
										out.println("<td>");
										out.println("&nbsp;");
										out.println("</td>");
										out.println("</tr>");
									}
								%>
							</table>

						</fieldset>
					</td>
				</tr>
			</table>
			<table width="100%" border="0">
				<tr>
					<td width="100%">
						<fieldset>
							<legend>
								Land of Gold
							</legend>

							<table width="100%" border="0" cellpadding="0" cellspacing="0">

								<%
									int numColumns = 5;
									int numRow = 3;
									for (int t = 0; t < numRow; t++) {

										out.println("<tr>");
										for (int tt = 0; tt < numColumns; tt++) {
											int index = t * numColumns + tt;
											out.println("<td width=\"20%\">");
											com.lostus.ejb.location.Location l = (com.lostus.ejb.location.Location) locationList
													.get(index);

											if (locationId == l.getLid()) {
												out
														.print("<input type=\"button\" name=\"l"
																+ index
																+ "\" id=\"l"
																+ index
																+ "\" style=\"width: 100%;background-color:orange\" ");
											} else {
												out
														.print("<input type=\"button\" name=\"l"
																+ index
																+ "\" id=\"l"
																+ index
																+ "\" style=\"width:100%;background-color:lightgreen\" ");
											}
											out.print("value=");

											out.print("\"" + l.getLname() + "\" ");
											out.print("onClick=\"generateMarket(");
											out.print(l.getLid());
											out.print(")\" />");

											out.println("</td>");
										}
										out.println("</tr>");
									}
								%>
							</table>
							<table>
								<tr>
									<td>
										<input type="hidden" id="boss" name="boss"
											value='Boss Coming!' />
									</td>
									<td>
										<input type="button" id="restart" name="restart"
											value='Restart!' onClick="restartGame();" style="width: 100%" />
									</td>
									<td>
										<input type="button" id="invite" name="invite"
											value='Challenge Friends!' onClick="invite();"
											style="width: 100%" />
									</td>
									<td>
										<input type="hidden" id="viewrecord" name="viewrecord"
											value='View Record!' onClick="ViewRecord();"
											style="width: 100%" />
									</td>
									<td>
										<form id="moveFormId" name="moveFormName"
											action=<%=fBCallbackURL%> method="post">

											<input type="hidden" id="userActionMoveId" name="userAction"
												value='' />
											<input type="hidden" id="locationMoveId"
												name="location" value=<%=locationId%> />
												
											<input type="hidden" id="isRefreshMoveId" name="isRefresh"/>

										</form>
										
										<form id="inviteFormId" name="inviteFormName"
											action=<%=fBCallbackURL%> method="post">

											<input type="hidden" id="userActionInviteId" name="userAction"
												value='' />

										</form>
										
										<form id="restartFormId" name="restartFormName"
											action=<%=fBCallbackURL%> method="post">

											<input type="hidden" id="userActionRestartId" name="userAction"
												value='' />

										</form>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>

		</td>
	</tr>
</table>



<script type="text/javascript" src=<%=jsURL%>>


</script>



