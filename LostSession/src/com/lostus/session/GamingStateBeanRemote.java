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

import java.util.List;

import javax.ejb.Remote;

import com.lostus.ejb.advertising.Advertising;
import com.lostus.ejb.auction.Auction;
import com.lostus.ejb.commodity.Commodity;
import com.lostus.ejb.event.Event;
import com.lostus.ejb.location.Location;
import com.lostus.ejb.news.News;
import com.lostus.ejb.record.Record;
import com.lostus.ejb.shopping.Shopping;
import com.lostus.ejb.user.User;
import com.lostus.ejb.usercart.UserCart;

@Remote
public interface GamingStateBeanRemote {

	public void setLocationId(int locationId);
	public int getLocationId();
	public void setTTL(int ttl);
	public int getTTL();
	public void setUserId(int userId);
	public int getUserId();
	public void setIsNewUser(int isNewUser);
	public int getIsNewUser();
	public void setSessionKey(String sessionKey);
	public void setAPIKey(String API_Key);
	public void setSECRETKey(String SECRET_Key);
	public String getSessionKey();
	public String getAPIKey();
	public String getSECRETKey();
	//public void setFacebookXmlRestClient();
	//public FacebookXmlRestClient getFacebookXmlRestClient();
	public void setShoppingList(List<Shopping> shoppingList);
	public List<Shopping> getShoppingList();
	public void setCartList(List<UserCart> cartList);
	public List<UserCart> getCartList();
	public void setLocationList(List<Location> location);
	public List<Location> getLocationList();
	public void setNewsList(List<News> newsList);
	public List<News> getNewsList();
	public void setCash(int cash);
	public int getCash();
	public void setDeposit(int deposit);
	public int getDeposit();
	public void setDebt(int debt);
	public int getDebt();
	public void setHealth(int health);
	public int getHealth();
	public void setReputation(int reputation);
	public int getReputation();
	public void setUser(User user);
	public User getUser();
	public void setCommodityList(List<Commodity> commodityList);
	public List<Commodity> getCommodityList();
	public void setEventList(List<Event> eventList);
	public List<Event> getEventList();
	
	public Event getEvent(int index);
	public void addEvent(Event event);
	public void updateEvent(int index, Event event);
	public void removeEvent(int index);
	
	public Commodity getCommodity(int index);
	public void addCommodity(Commodity commodity);
	public void updateCommodity(int index, Commodity commodity);
	public void removeCommodity(int commodity);
	
	public Shopping getShopping(int index);
	public void addShopping(Shopping shopping);
	public void updateShopping(int index, Shopping shopping);
	public void removeShopping(int index);
	
	public UserCart getCart(int index);
	public void addCartt(UserCart userCart);
	public void updateCart(int index, UserCart userCart);
	public void removeCart(int index);
	
	public Location getLocation(int index);
	public void addLocation(Location location);
	public void updateLocation(int index, Location location);
	public void removeLocation(int index);
	
	public News getNews(int index);
	public void addNews(News news);
	public void updateNews(int index, News news);
	public void removeNews(int index);
	
	public void setIsInit();
	public int getIsInit();
	
	public Advertising getAdvertising(int index);
	public void addAdvertising(Advertising advertising);
	public void updateAdvertising(int index, Advertising advertising);
	public void removeAdvertising(int index);
	public void setAdvertisingList(List<Advertising> advertisingList);
	public List<Advertising> getAdvertisingList();
	
	public void setIsGameOver(int isGameOver);
	public int getIsGameOver();
	
	public Record getRecord(int index);
	public void addRecord(Record record);
	public void updateRecord(int index, Record record);
	public void removeRecord(int index);
	public void setRecordList(List<Record> recordList);
	public List<Record> getRecordList();
	public List<Record> getSortedRecordList();
	
	public Auction getAuction(int index);
	public void addAuction(Auction auction);
	public void updateAuction(int index, Auction auction);
	public void removeAuction(int index);
	public void setAuctionList(List<Auction> auctionList);
	public List<Auction> getAuctionList();
	
	public List<User> getAllUserList();
	public void setAllUserList(List<User> userList);
	
	public void setSendEmail(int isSendEmail);
	public int getSendEmail();
	
	public void setIsRefresh(String isRefresh);
	public String getIsRefresh();
	
	
}
