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
	String bankingBackgroundImageURL = bkURL + "bank.jpg";

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

	String mainPageURL = fBCallbackURL + "?page=main";
	String bankingPageURL = fBCallbackURL + "?page=banking";
	String auctionPageURL = fBCallbackURL + "?page=auction";
	String statusPageURL = fBCallbackURL + "?page=status";
%>


<fb:tabs>
	<fb:tab-item href=<%=mainPageURL%> title='Main' />
	<fb:tab-item href=<%=bankingPageURL%> title='Trade' selected='true' />
	<!-- <fb:tab-item href=<%=auctionPageURL%> title='Auction' /> -->
	<fb:tab-item href=<%=statusPageURL%> title='Status' />
</fb:tabs>

<fieldset>
	<legend style="color: red; font-weight:bold;">
		Bank of the Poor
	</legend>
	<table width="100%" border="0" style="background: url(<%=bankingBackgroundImageURL %>) no-repeat" >
		<tr>
			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							I have:
							<br />
							Cash: $
							<%=userCash%>
							<br />
							Deposit: $
							<%=userDeposit%>
						</td>
					</tr>

				</table>
			</td>


			<td width="40%">
				<table width="100%">
					<tr>
						<td colspan="2">
							<input type="text" id="bankInputId" name="bankInputName"
								value='0' style="width: 100%" />
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" id="depositButtonId"
								name="depositButtonName" value='DEPOSIT' onClick="deposit();"
								style="width: 100%" />
						</td>
						<td>
							<input type="button" id="withdrawButtonId"
								name="withdrawButtonName" value='WITHDRAW' onClick="withdraw();"
								style="width: 100%" />
						</td>
					</tr>

				</table>
			</td>

			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							Here is the comment from the banker.
						</td>
					</tr>

				</table>
			</td>

		</tr>


	</table>
</fieldset>




<fieldset>
	<legend style="color: red; font-weight:bold;">
		Get some needy money from Pawn Shop
	</legend>
	<table width="100%" border="0">
		<tr>
			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

				</table>
			</td>


			<td width="40%">
				<table width="100%">
					<tr>
						<td>
							<input type="text" id="pawnshopInputId" name="pawnshopInputName"
								value='0' style="width: 100%" />
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" id="pawnshopButtonId"
								name="pawnshopButtonName" value='GIVE ME the MONEY' onClick="pawn();"
								style="width: 100%" />
						</td>
					</tr>

				</table>
			</td>

			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							Here is the comment from the banker.
						</td>
					</tr>

				</table>
			</td>

		</tr>


	</table>
</fieldset>




<fieldset>
	<legend style="color: red; font-weight:bold;">
		Trade Health for Money
	</legend>
	<table width="100%" border="0">
		<tr>
			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							Am I healthy?
							<br />
							Health: 
							<%=userHealth%>
						</td>
					</tr>

				</table>
			</td>


			<td width="40%">
				<table width="100%">
					<tr>
						<td colspan="2">
							<input type="text" id="healtyInputId" name="healtyInputName"
								value='0' style="width: 100%" />
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" id="buyHealtyButtonId"
								name="buyHealtyButtonName" value='STRONGER' onClick="buyHealty();"
								style="width: 100%" />
						</td>
						<td>
							<input type="button" id="sellHealtyButtonId"
								name="sellHealthButtonName" value='WEAKER' onClick="sellHealth();"
								style="width: 100%" />
						</td>
					</tr>

				</table>
			</td>

			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							Here is the comment from the banker.
						</td>
					</tr>

				</table>
			</td>

		</tr>


	</table>
</fieldset>




<fieldset>
	<legend style="color: red; font-weight:bold;">
		Trade Reputation for Money
	</legend>
	<table width="100%" border="0">
		<tr>
			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							I have:
							<br />
							Cash: $
							<%=userReputation%>
						</td>
					</tr>

				</table>
			</td>


			<td width="40%">
				<table width="100%">
					<tr>
						<td colspan="2">
							<input type="text" id="reputationInputId" name="reputationInputName"
								value='0' style="width: 100%" />
						</td>
					</tr>

					<tr>
						<td>
							<input type="button" id="buyReputationButtonId"
								name="buyReputationButtonName" value='BE FAMOUS' onClick="buyReputation();"
								style="width: 100%" />
						</td>
						<td>
							<input type="button" id="sellReputationButtonId"
								name="sellReputationButtonName" value='BAD BOY' onClick="sellReputation();"
								style="width: 100%" />
						</td>
					</tr>

				</table>
			</td>

			<td width="30%">
				<table width="100%" border="1">
					<tr>
						<td>

							<img src=<%=picture%> width="100" height="100" />
						</td>

					</tr>

					<tr>
						<td>
							Here is the comment from the banker.
						</td>
					</tr>

				</table>
			</td>

		</tr>


	</table>
</fieldset>
