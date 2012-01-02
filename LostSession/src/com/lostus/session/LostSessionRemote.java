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

import javax.ejb.Remote;
import javax.servlet.http.HttpServletRequest;

import com.lostus.ejb.advertising.Advertising;
import com.lostus.ejb.auction.Auction;
import com.lostus.ejb.event.Event;
import com.lostus.ejb.news.News;
import com.lostus.ejb.record.Record;
import com.lostus.ejb.shopping.Shopping;
import com.lostus.ejb.user.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

import java.sql.Timestamp;
@Remote
public interface LostSessionRemote {
	public void getDBConn();
	public int getData();
	public void setData(int data);
	public String getRespMsg(Map params, Map headers, String inMsg);
	
	public void setUserSessionId(int userId, String sessionId);
	public List getMarketList(int userId);
	public int getUserIdBySessionId(String sessionId);
	public String getSessionIdByUserId(int userId);
	public List getCartList(int userId);
	public String getCNameByCId(int cId);
	
	public int isUserExist(int userId);
	public void addNewUser(int userId, String name);
	public void updateCommodityQuantity(int userId, int cId, int cQuantity);
	public void updateCart(int userId, int cId, int cQuantity, int cPrice);
	public void updateCartSell(int userId, int cId, int cQuantity, int cPrice);
	public User getUserById(int uId);
	public List getLocationByType(int lType);
	public List<Shopping> getMarketListByuIdlId(int userId, int locationId);
	public void updateCommoditylId(int userId, int lId);
	public int updateUserMoney(int userId, int userCash, int isBuy);
	public void updateUserTTL(int userId, int TTL);
	public void updateUserCapacity(int userId, int quantity, int isBuy);
	public void resetUser(int userId);
	public void resetCart(int userId);
	public void resetMarket(int userId, int locationId);
	public Event getEvent(int function, int code);
	public void updateFirstNews(int userId, int priority, int type, String content, int property);
	public List<News> getNewsListByUid(int userId);
	public int updateUserDeposit(int userId, int userCash, int isDeposit);
	public GamingStateBeanRemote getGS();
	
	public void updateCommodityQuantityGS(int userId, int cId, int cQuantity);
	public void updateCartGS(int userId, int cId, int cQuantity, int cPrice);
	public void updateCartSellGS(int userId, int cId, int cQuantity, int cPrice);
	public User getUserByIdGS(int uId);
	public List getLocationByTypeGS(int lType);
	public List<Shopping> getMarketListByuIdlIdGS(int userId, int locationId);
	public void updateCommoditylIdGS(int userId, int lId);
	public int updateUserMoneyGS(int userId, int userCash, int isBuy);
	public void updateUserTTLGS(int userId, int TTL);
	public void updateUserCapacityGS(int userId, int quantity, int isBuy);
	public void resetUserGS(int userId);
	public void resetCartGS(int userId);
	public void resetMarketGS(int userId, int locationId);
	public Event getEventGS(int function, int code);
	public void updateFirstNewsGS(int userId, int priority, int type, String content, int property);
	public List<News> getNewsListByUidGS(int userId);
	public int updateUserDepositGS(int userId, int userCash, int isDeposit);
	
	public void setUserSessionIdGS(int userId, String sessionId);
	public List getMarketListGS(int userId);
	public int getUserIdBySessionIdGS(String sessionId);
	public String getSessionIdByUserIdGS(int userId);
	public List getCartListGS(int userId);
	public String getCNameByCIdGS(int cId);
	
	
	public void processRequest(Map<String, String> requestParams);
	
	public Advertising getAdvertisingByID(int adtype, int adid, int property);
	public Advertising getAdvertisingByIDGS(int adtype, int adid, int property);
	public List<Advertising> getAdvertisingByAdType(int adtype);
	public List<Advertising> getAdvertisingByAdTypeGS(int adtype);
	
	public List<Record> getAllRecord();
	public List<Record> getAllRecordGS();
	public List<Record> getSortedRecordGS();
	public void addRecord(int userId, String uname, int cash, int deposit, int health, int reputation, int debt, Date timestamp);
	public void addRecordGS(int userId, String uname, int cash, int deposit, int health, int reputation, int debt, Date timestamp);
	
	public int updateUserHealthGS(int userId, int userHealth, int isDecrease);
	public int updateUserReputationGS(int userId, int userReputation, int isDecrease);
	
	public List<Auction> getOnGoAuction();
	public List<Auction> getOnGoAuctionGS();
	public List<Auction> getYourBids(int userId);
	public List<Auction> getYourBidsGS(int userId);
	public List<Auction> getYourAuction(int userId);
	public List<Auction> getYourAuctionGS(int userId);
	public void makeYourBid(int cId, int uId, int bidUserId, int bidPrice, int bidTime);
	public void makeYourAuction(int cId, int uId, int originalPrice, int unitPrice, int quantity, int ttl);
	public List<Auction> getOnGoAuctionWithMaxBidPrice();
	public List<Integer> getMaxBidPrices(List<Integer> cIds, List<Integer> uIds);
	public void removeAuction(int cId, int uId, int bidUId);
	public void updateAuctionTTL(int userId);
	
	public void updateCartAuctionGS(int userId, int cId, int cQuantity, int cOriginalPrice);
	
	public void cleanAuction(int userId);
	
	public List<Advertising> getAllAdvertising();
	public List<User> getAllUser();
}
