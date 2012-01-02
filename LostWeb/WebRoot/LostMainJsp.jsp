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
<%@ page import="com.lostus.ejb.user.*"%>
<%@ page import="com.lostus.ejb.shopping.*"%>
<%@ page import="com.lostus.ejb.usercart.*"%>
<%@ page import="com.lostus.ejb.location.*"%>
<%@ page import="com.lostus.ejb.news.*"%>
<%@ page import="com.lostus.ejb.auction.*"%>
<%@ page import="com.facebook.api.schema.*"%>
<%@ page import="com.facebook.api.*"%>
<%@ page import="org.w3c.dom.Document"%>
<%
	System.out.println("##########################In JSP");
	//The Java code load game information for presentation
	LostHolder holder = null;
	int userId = 0;
	List<Shopping> commodityList = null;
	List<UserCart> cartList = null;

	int userCash = 0;
	int userDeposit = 0;
	int userDebt = 0;
	int userHealth = 0;
	int userReputation = 0;
	int userTTL = 0;
	int userCapacity = 0;

	List<com.lostus.ejb.location.Location> location = null;
	int locationId = 0;

	List<News> newsList = null;

	List<Auction> onGoAuctionList = null;
	List<Auction> yourBidList = null;
	List<Auction> yourAuctionList = null;

	List<Integer> yourBidListMaxPrice = null;
	List<Integer> yourAuctionListMaxPrice = null;

	List<Integer> cIdList1 = new ArrayList<Integer>();
	List<Integer> uIdList1 = new ArrayList<Integer>();

	List<Integer> cIdList2 = new ArrayList<Integer>();
	List<Integer> uIdList2 = new ArrayList<Integer>();

	holder = (LostHolder) request.getAttribute("LostHolder");
	String picture = (String) request.getAttribute("picture");
	userId = holder.getUserId();

	//Get the request URL
	String bkURL = holder.getRequestURL() + "figures/bkg/";
	String ads1URL = holder.getRequestURL() + "figures/ads1/";
	String ads2URL = holder.getRequestURL() + "figures/ads2/";
	String fBCallbackURL = holder.getFBCallbackURL();

	//--------------------------------------------------------------------Get game state information

	//Initialize User Information. Currently these actions involve database.
	//Later for performance concern, user information can be store in holder.
	locationId = holder.getLsr().getGS().getLocationId();
	//System.out.println("The ID is: " + locationId);

	com.lostus.ejb.user.User u = holder.getLsr().getGS().getUser();
	userCash = u.getUmoney();
	userDeposit = u.getUdeposit();
	userDebt = u.getUdebt();
	userHealth = u.getUhealth();
	userReputation = u.getUreputation();
	userTTL = u.getUttl();
	userCapacity = u.getUcapacity();
	//Initialize Location Information
	location = holder.getLsr().getLocationByTypeGS(0);
	//Get the commodity and cart list
	commodityList = holder.getLsr().getMarketListByuIdlIdGS(userId,
			locationId);
	cartList = holder.getLsr().getCartListGS(userId);

	//Get the news list
	newsList = holder.getLsr().getNewsListByUidGS(userId);

	/*
	//Get the auction list
	onGoAuctionList=holder.getLsr().getOnGoAuctionWithMaxBidPrice();
	yourBidList=holder.getLsr().getYourBids(userId);
	yourAuctionList=holder.getLsr().getYourAuction(userId);
	
	for(int t=0; t<yourBidList.size(); t++){
	cIdList1.add(t, yourBidList.get(t).getId().getCid());
	uIdList1.add(t, yourBidList.get(t).getId().getUid());
	}
	yourBidListMaxPrice=holder.getLsr().getMaxBidPrices(cIdList1, uIdList1);
	
	for(int t=0; t<yourAuctionList.size(); t++){
	cIdList2.add(t, yourAuctionList.get(t).getId().getCid());
	uIdList2.add(t, yourAuctionList.get(t).getId().getUid());
	}
	yourAuctionListMaxPrice=holder.getLsr().getMaxBidPrices(cIdList2, uIdList2);
	 */

	FacebookRestClient client = holder.getFacebookRestClient();
%>

<%
	com.lostus.ejb.location.Location lll = (com.lostus.ejb.location.Location) location
			.get(locationId - 13);
	out.println("<table background=\"" + bkURL + lll.getLname()
			+ ".jpg" + "\">");
%>


<tr>
	<td>
		<form name="fMarket" method="post" action="">
			<table border="0">
				<tr>
					<td>
						<fieldset>
							<legend>
								Black Market
							</legend>
							<table id="table_CommodityListId" style="width: 7cm">
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
														+ shopItem.getCprice() + "\" />");
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

							<input type="hidden" id="commodityInId" name="commodityInName"
								value='' />
						</fieldset>
					</td>
					<td align="center" style="width: 1.5cm">
						<fieldset>
							Quantity
							<br />
							<input type="text" id="quantity" name="quantity" value='0'
								style="width: 1cm" />

							<br />
							<br />
							<input type="button" id="buyId" name="buyName" value='BUY'
								onClick="buy();" style="width: 1.2cm" />
							<br />

							<br />
							<input id="sellId" name="sell" type="button" value="SELL"
								onClick="sell();" style="width: 1.2cm" />


						</fieldset>
					</td>
					<td>
						<fieldset>
							<legend>
								My warehouse(
								<%
								out.print("<span id=\"hasGoods\">");
								out.print(100 - userCapacity);
								out.print("</span>");
							%>
								/
								<span id="roomtotal">100</span>
								<input type="hidden" id="roomtotal_hide" name="roomtotal_hide"
									value="100" />
								)
							</legend>

							<select name="cartList" size="10" id="cartList"
								style="width: 8cm">
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
										out.print("Q" + uC.getQuantity());
										out.println("</option");
									}
								%>
							</select>
							<input type="hidden" id="beginSalesGoods" name="beginSalesGoods"
								value='' />
						</fieldset>
					</td>

				</tr>
			</table>
		</form>

		<table border='0'>
			<tr>
				<td style="width: 6cm">
					<fieldset>
						<legend>
							You've been living for
							<%
							out.print("<span id=\"hmDay\">");
							out.print(40 - userTTL);
							out.print("</span>/40 days");
						%>
						</legend>
						<table width="100%" cellpadding="2" cellspacing="5">

							<tr>
								<td width="30" height="30" align="left" nowrap="nowrap">
									Cash
								</td>
								<td colspan="3" class="green">
									<%
										out.println("<span id=\"cash\">");
										out.println(userCash);
										out.println("</span>");
									%>
									<input type="hidden" id="userCash" name="userCash"
										value="<% out.println(userCash); %>" />

								</td>
								<td rowspan="5">
									<img src=<%=picture%> alt="Angry" width="100" height="100" />
								</td>
							</tr>
							<tr>
								<td height="30" nowrap="nowrap">
									Deposit
								</td>
								<td colspan="3" class="green">
									<%
										out.println("<span id=\"deposit\">");
										out.println(userDeposit);
										out.println("</span>");
									%>
								</td>
							</tr>
							<tr>
								<td height="30" nowrap="nowrap">
									Debt
								</td>

								<td colspan="3" class="reg">
									<%
										out.println("<span id=\"debt\">");
										out.println(userDebt);
										out.println("</span>");
									%>
								</td>
							</tr>
							<tr>
								<td height="30" nowrap="nowrap">
									Health
								</td>
								<td width="76" class="green">
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
								<td width="76" class="green">
									<%
										out.println("<span id=\"reputation\">");
										out.println(userReputation);
										out.println("</span>");
									%>
								</td>
							</tr>
						</table>
					</fieldset>
				</td>
				<td style="width: 12cm; height: 5cm">
					<fieldset>
						<legend>
							Millionaire's News of Today
						</legend>

						<table id="table_NewsListId" style="width: 9.5cm">
							<%
								News news = null;
								for (int t = 0; t < newsList.size(); t++) {
									news = (News) newsList.get(t);

									//row
									out.println("<tr>");

									//First column
									out.println("<td style=\"width: 6.5cm\" >");
									out.println("* " + news.getContent());

									out.println("</td>");

									//Second column

									out.println("<td style=\"width: 3cm\">");

									String linkAddr = holder.getLsr().getAdvertisingByIDGS(2,
											news.getIdInType(), news.getProperty()).getAdlink();
									String imgAddr = holder.getLsr().getAdvertisingByIDGS(2,
											news.getIdInType(), news.getProperty()).getAdimage();

									int idToShow = 0;
									if (news.getType().intValue() > 100) {
										idToShow = news.getType();

										if (imgAddr.equalsIgnoreCase("own")) {
											imgAddr = picture;

											out
													.print("<a href=\""
															+ linkAddr
															+ "\" target=\"_blank\"> <img src=\""
															+ imgAddr
															+ "\" alt=\"Angry\" width=\"50\" height=\"50\"/> </a>");

										} else {

											
											EnumSet<ProfileField> fields = EnumSet.of(
													com.facebook.api.ProfileField.NAME,
													com.facebook.api.ProfileField.PIC);

											Collection<Long> users = new ArrayList<Long>();
											users.add(Long.valueOf(Integer.toString(idToShow)));

											Document d = client.users_getInfo(users, fields);
											imgAddr = d.getElementsByTagName("pic").item(0)
													.getTextContent();

											linkAddr = linkAddr+"?id="+idToShow;

											out.print("<a href=\""
															+ linkAddr
															+ "\" target=\"_blank\"> <img src=\""
															+ imgAddr
															+ "\" alt=\"Angry\" width=\"50\" height=\"50\"/> </a>");
										}

									} else {

										out
												.print("<a href=\""
														+ linkAddr
														+ "\" target=\"_blank\"> <img src=\""
														+ ads2URL
														+ imgAddr
														+ "\" alt=\"Angry\" width=\"50\" height=\"50\"/> </a>");
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
		<table>
			<tr>
				<%
					out
							.print("<input type=\"hidden\" id=\"currentLocation\" name=\"currentLocation\" ");
					out.print("value=\"" + locationId + "\"" + "/>");
					out
							.print("<input type=\"hidden\" id=\"callbackurl\" name=\"callbackurl\" ");
					out.print("value=\"" + fBCallbackURL + "\"" + "/>");
					out.print("<input type=\"hidden\" id=\"userId\" name=\"userId\" ");
					out.print("value=\"" + userId + "\"" + "/>");

					out
							.print("<input type=\"hidden\" id=\"userCapacity\" name=\"userCapacity\" ");
					out.print("value=\"" + userCapacity + "\"" + "/>");
				%>
				<td style="width: 18cm">
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
										com.lostus.ejb.location.Location l = (com.lostus.ejb.location.Location) location
												.get(index);

										if (locationId == l.getLid()) {
											out
													.print("<input type=\"button\" name=\"l"
															+ index
															+ "\" id=\"l"
															+ index
															+ "\" style=\"width:3cm;background-color:orange\" ");
										} else {
											out
													.print("<input type=\"button\" name=\"l"
															+ index
															+ "\" id=\"l"
															+ index
															+ "\" style=\"width:3cm;background-color:lightgreen\" ");
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
									<input type="hidden" id="boss" name="boss" value='Boss Coming!' />
								</td>
								<td>
									<input type="button" id="restart" name="restart"
										value='Restart!' onClick="RestartGame();" style="width: 2.5cm" />
								</td>
								<td>
									<input type="button" id="invite" name="invite"
										value='Invite Friends!' onClick="invite();"
										style="width: 2.5cm" />
								</td>
								<td>
									<input type="hidden" id="viewrecord" name="viewrecord"
										value='View Record!' onClick="ViewRecord();"
										style="width: 2.5cm" />
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

<script type="text/javascript">

//User buy from market

function buy() {
	var cL = document.getElementById("currentLocation").getValue();
	var userCapacity = document.getElementById("userCapacity").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	
	var buyInfo = null;
	var commodityTable = document.getElementById("table_CommodityListId");
	var commodityRadios = commodityTable.getElementsByTagName("input");
	//document.setLocation("http://www.google.com/?"+commodityRadios.length);
	for (var i = 0; i < commodityRadios.length; i++) {
		if (commodityRadios[i].getChecked()) {
			buyInfo = commodityRadios[i].getValue();
		}
	}
	if (buyInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to buy in the Market.");
		return;
	}
	var cQuantityBuy = document.getElementById("quantity").getValue();
	if (cQuantityBuy == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (cQuantityBuy < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(cQuantityBuy) != cQuantityBuy) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	var t = buyInfo.indexOf("_");
	var price = buyInfo.substring(t + 1, buyInfo.length);
	var userCash = document.getElementById("userCash").getValue();
	if (price * cQuantityBuy > userCash) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "You don't have enough money man. Please input appropriate quantity.");
		return;
	}
	
	//document.setLocation("http://www.google.com/?"+cQuantityBuy+":"+userCapacity);
	
	if(parseInt(cQuantityBuy) > parseInt(userCapacity)){
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Warehouse capacity exceeded. Maximum you can buy is "+userCapacity);
		return;
	}
	
 //document.setLocation("http://www.google.com/?pp="+uC);
	document.setLocation(cURL+"?userAction=buy&buyInfo=" + buyInfo + "&cQuantityBuy=" + cQuantityBuy + "&location=" + cL);
}

//User sell from cart
function sell() {
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var sellInfo = document.getElementById("cartList").getValue();
	if (sellInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to buy in the Cart.");
		return;
	}
	var cQuantitySell = document.getElementById("quantity").getValue();
	if (cQuantitySell == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (cQuantitySell < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(cQuantitySell) != cQuantitySell) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	var sellPrice=0;
	
	var t = sellInfo.indexOf("_");
	var sellId = sellInfo.substring(0, t);
	var sellPriceQuantity = sellInfo.substring(t + 1, sellInfo.length);
	var tt = sellPriceQuantity.indexOf("_");
	var sellOriginalPrice = sellPriceQuantity.substring(0, tt);
	var sellQuantity = sellPriceQuantity.substring(tt + 1, sellPriceQuantity.length);
	
	if(cQuantitySell>parseInt(sellQuantity)){
	    var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity smaller than you have.");
		return;
	}
	
	//document.setLocation("http://www.google.com/?"+sellId+" "+sellPrice+" "+sellQuantity);
	//return;
	var commodityTable = document.getElementById("table_CommodityListId");
	var commodityRadios = commodityTable.getElementsByTagName("input");
	//document.setLocation("http://www.google.com/?"+commodityRadios.length);
	var flag = 0;
	for (var i = 0; i < commodityRadios.length; i++) {
		var buyInfo = commodityRadios[i].getValue();
		var ttt = buyInfo.indexOf("_");
		var itemId = buyInfo.substring(0, ttt);
		if (itemId == sellId) {
			sellPrice = buyInfo.substring(ttt + 1, buyInfo.length);
			sellInfo = sellId + "_" + sellPrice + "_" + sellQuantity + "_" + sellOriginalPrice;
			flag = 1;
		}
	}
	if (flag == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Sorry, item can be sold only when it is available in market.");
		return;
	}
	
	//document.setLocation("http://www.google.com/?"+sellInfo);
	//return;
	document.setLocation(cURL+"?userAction=sell&sellInfo=" + sellInfo + "&cQuantitySell=" + cQuantitySell + "&location=" + cL);
}

//User move
function generateMarket(k) {
	var cURL = document.getElementById("callbackurl").getValue();
	//var msg="?userAction=move&location="+k;
	//sendRequest(cURL, msg);
	document.setLocation(cURL+"?userAction=move&location=" + k);
}

//User sell in the pawn shop
function pawn() {
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var sellInfo = document.getElementById("cartList").getValue();
	if (sellInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to buy in your warehouse.");
		return;
	}
	var cQuantitySell = document.getElementById("quantity").getValue();
	if (cQuantitySell == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (cQuantitySell < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(cQuantitySell) != cQuantitySell) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	document.setLocation(cURL+"?userAction=pawn&sellInfo=" + sellInfo + "&cQuantitySell=" + cQuantitySell + "&location=" + cL);
}

//User deposit
function deposit() {
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var amount = document.getElementById("bnkAmount").getValue();
	if (amount == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate amount other than 0");
		return;
	}
	document.setLocation(cURL+"?userAction=deposit&amount=" + amount + "&location=" + cL);
}

//User withdraw
function withdraw() {
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var amount = document.getElementById("bnkAmount").getValue();
	if (amount == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate amount other than 0");
		return;
	}
	document.setLocation(cURL+"?userAction=withdraw&amount=" + amount + "&location=" + cL);
}


//User restart the game
function RestartGame() {
	var cURL = document.getElementById("callbackurl").getValue();
	document.setLocation(cURL+"?userAction=restart");
}

//Save the game
function SaveGame() {
	var cURL = document.getElementById("callbackurl").getValue();
	document.setLocation(cURL+"?userAction=save");
}

//bid
function bid() {
	var cL = document.getElementById("currentLocation").getValue();
	var userId = document.getElementById("userId").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var bidInfo = document.getElementById("allAuctionsSelectId").getValue();
	var userCapacity = document.getElementById("userCapacity").getValue();
	
	if (bidInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to bid.");
		return;
	}
	
	var t = bidInfo.indexOf("_");
	var cId = bidInfo.substring(0, t);
	var bidUidPriceQuantity = bidInfo.substring(t + 1, bidInfo.length);
	var tt = bidUidPriceQuantity.indexOf("_");
	var auctionUserId = bidUidPriceQuantity.substring(0, tt);
	var bidPriceQuantity=bidUidPriceQuantity.substring(tt + 1, bidUidPriceQuantity.length);
	var ttt=bidPriceQuantity.indexOf("_");
	var bidPrice = bidPriceQuantity.substring(0, ttt);
	var bidQuantity = bidPriceQuantity.substring(ttt + 1, bidPriceQuantity.length);
	
	
	if(userId==auctionUserId){
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "You cannot bid your auction.");
		return;
	}
	
	var cUnitPrice = document.getElementById("unitPriceId").getValue();
	
	if (cUnitPrice == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price other than 0");
		return;
	}
	if (cUnitPrice < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price other than negative");
		return;
	}
	if (Math.floor(cUnitPrice) != cUnitPrice) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price.");
		return;
	}
	
	if(parseInt(bidPrice)>=parseInt(cUnitPrice)){
	    var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Your bid must be greater than current max bid.");
		return;
	}
	
	var userCash = document.getElementById("userCash").getValue();
	if (cUnitPrice * parseInt(bidQuantity) > userCash) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "You don't have enough money man. Please input appropriate quantity.");
		return;
	}
	
	if(parseInt(bidQuantity) > parseInt(userCapacity)){
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Warehouse capacity exceeded. Maximum you can buy is "+userCapacity);
		return;
	}
	
	
	document.setLocation(cURL+"?userAction=bid&bidInfo=" + bidInfo + "&cUnitPrice=" + cUnitPrice + "&location=" + cL);
}

//auction
function auction() {
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	var auctionInfo = document.getElementById("cartList").getValue();
	if (auctionInfo == null) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please pick up the item you would like to sell in your warehouse.");
		return;
	}
	var cUnitPrice = document.getElementById("sellAuctionPriceId").getValue();
	var cQuantity = document.getElementById("sellAuctionQuantityId").getValue();
	var cTTL = document.getElementById("sellAuctionTTLId").getValue();
	
	if (cUnitPrice == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price other than 0");
		return;
	}
	if (cUnitPrice < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price other than negative");
		return;
	}
	if (Math.floor(cUnitPrice) != cUnitPrice) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate price.");
		return;
	}
	
	
	if (cQuantity == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than 0");
		return;
	}
	if (cQuantity < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity other than negative");
		return;
	}
	if (Math.floor(cQuantity) != cQuantity) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity.");
		return;
	}
	
	
	if (cTTL == 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate auction span other than 0");
		return;
	}
	if (cTTL < 0) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate auction span other than negative");
		return;
	}
	if (Math.floor(cTTL) != cTTL) {
		var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate auction span.");
		return;
	}
	
	var t = auctionInfo.indexOf("_");
	var auctionId = auctionInfo.substring(0, t);
	var auctionPriceQuantity = auctionInfo.substring(t + 1, auctionInfo.length);
	var tt = auctionPriceQuantity.indexOf("_");
	var auctionOriginalPrice = auctionPriceQuantity.substring(0, tt);
	var auctionOriginalQuantity = auctionPriceQuantity.substring(tt + 1, auctionPriceQuantity.length);
	
	if(cQuantity>parseInt(auctionOriginalQuantity)){
	    var dialog = new Dialog(Dialog.DIALOG_POP).showMessage("POPUP", "Please input appropriate quantity smaller than you have.");
		return;
	}
	
	

	
	
	
	
	document.setLocation(cURL+"?userAction=auction&auctionInfo=" + auctionInfo + "&cUnitPrice=" + cUnitPrice + "&cQuantity=" + cQuantity+ "&cTTL=" + cTTL+ "&location=" + cL);
}

//invite friends
function invite(){
	var cL = document.getElementById("currentLocation").getValue();
	var cURL = document.getElementById("callbackurl").getValue();
	document.setLocation(cURL+"?userAction=invite");
}

	
	function sendRequest(url, msg)
	{
		var ajax = new Ajax();
		ajax.post(url+msg);
		document.setLocation(url);
	}



</script>

