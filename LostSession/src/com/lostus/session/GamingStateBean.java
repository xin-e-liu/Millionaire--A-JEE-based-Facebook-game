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

import java.io.Serializable;

import javax.ejb.Stateful;


import com.lostus.ejb.commodity.*;
import com.lostus.ejb.event.*;
import com.lostus.ejb.location.*;
import com.lostus.ejb.news.*;
import com.lostus.ejb.shopping.*;
import com.lostus.ejb.user.*;
import com.lostus.ejb.usercart.*;
import com.lostus.ejb.advertising.*;
import com.lostus.ejb.record.*;
import com.lostus.ejb.auction.*;

import java.util.*;

@Stateful
public class GamingStateBean implements Serializable, GamingStateBeanRemote {

	private String isRefresh="0";
	
	//Facebook related enviornments
	private int userId;
	
	private String sessionKey;
	
	private String API_Key;
	
	private String SECRET_Key;
	
	//Gaming related enviornments
	private int isInit=0;
	
	private int locationId;
	
	private User user;
	
	private int isGameOver=0;
	
	private int sendEmail=0;
	
	//---------------------------------------------Depredated
	private int ttl;
	
	private int cash;
	
	private int deposit;
	
	private int debt;
	
	private int health;
	
	private int reputation;
	
	//--------------------------------------------------------
	
	private int isNewUser;
	
	private List<User> userList;
	
	private List<Shopping> shoppingList;
	
	private List<UserCart> cartList;
	
	private List<Location> locationList;
	
	private List<News> newsList;
	
	private List<Commodity> commodityList;
	
	private List<Event> eventList;
	
	private List<Advertising> advertisingList;
	
	private List<Record> recordList;
	
	private List<Auction> auctionList;
	
	public void setSendEmail(int isSendEmail){
		this.sendEmail=isSendEmail;
	}
	public int getSendEmail(){
		return this.sendEmail;
	}
	
	
	public void setIsInit(){
		this.isInit=1;
	}
	public int getIsInit(){
		return this.isInit;
	}
	
	public void setIsGameOver(int isGameOver){
		this.isGameOver=isGameOver;
	}
	
	public int getIsGameOver(){
		return this.isGameOver;
	}
	
	
	//---------------------------------------Methods for Event
	
	public Event getEvent(int index){
		return this.eventList.get(index);
	}
	
	public void addEvent(Event event){
		this.eventList.add(event);
	}
	
	public void updateEvent(int index, Event event){
		this.eventList.set(index, event);
	}
	
	public void removeEvent(int index){
		this.eventList.remove(index);
	}
	
	
	public void setEventList(List<Event> eventList){
		this.eventList=eventList;
	}
	
	public List<Event> getEventList(){
		return this.eventList;
	}
	
	//------------------------------------------------------
	
	//---------------------------------------Methods for Commodity
	
	public Commodity getCommodity(int index){
		return this.commodityList.get(index);
	}
	
	public void addCommodity(Commodity commodity){
		this.commodityList.add(commodity);
	}
	
	public void updateCommodity(int index, Commodity commodity){
		this.commodityList.set(index, commodity);
	}
	
	public void removeCommodity(int commodity){
		this.commodityList.remove(commodity);
	}
	
	public void setCommodityList(List<Commodity> commodityList){
		this.commodityList=commodityList;
	}
	
	public List<Commodity> getCommodityList(){
		return this.commodityList;
	}
	
	//------------------------------------------------------
	
	
	//---------------------------------------Methods for User
	public void setUser(User user){
		this.user=user;
	}
	
	public User getUser(){
		return this.user;
	}
	
	//------------------------------------------------------
	
	//---------------------------------------Methods for all Users
	public List<User> getAllUserList(){
		return this.userList;
	}
	
	public void setAllUserList(List<User> userList){
		this.userList=userList;
	}
	
	
	//------------------------------------------------------
	
	//---------------------------------------Methods for Shopping
	
	public Shopping getShopping(int index){
		return this.shoppingList.get(index);
	}
	
	public void addShopping(Shopping shopping){
		this.shoppingList.add(shopping);
	}
	
	public void updateShopping(int index, Shopping shopping){
		this.shoppingList.set(index, shopping);
	}
	
	public void removeShopping(int index){
		this.shoppingList.remove(index);
	}
	
	public void setShoppingList(List<Shopping> shoppingList){
		this.shoppingList=shoppingList;
	}
	
	public List<Shopping> getShoppingList(){
		return this.shoppingList;
	}
	//------------------------------------------------------
	
	
	//---------------------------------------Methods for Cart
	
	public UserCart getCart(int index){
		return this.cartList.get(index);
	}
	
	public void addCartt(UserCart userCart){
		this.cartList.add(userCart);
	}
	
	public void updateCart(int index, UserCart userCart){
		this.cartList.set(index, userCart);
	}
	
	public void removeCart(int index){
		this.cartList.remove(index);
	}
	
	public void setCartList(List<UserCart> cartList){
		this.cartList=cartList;
	}
	
	public List<UserCart> getCartList(){
		return this.cartList;
	}
	
	//------------------------------------------------------
	
	
	//---------------------------------------Methods for Location
	
	public Location getLocation(int index){
		return this.locationList.get(index);
	}
	
	public void addLocation(Location location){
		this.locationList.add(location);
	}
	
	public void updateLocation(int index, Location location){
		this.locationList.set(index, location);
	}
	
	public void removeLocation(int index){
		this.locationList.remove(index);
	}
	
	public void setLocationList(List<Location> locationList){
		this.locationList=locationList;
	}
	
	public List<Location> getLocationList(){
		return this.locationList;
	}
	
	//------------------------------------------------------
	
	//---------------------------------------Methods for News
	
	public News getNews(int index){
		return this.newsList.get(index);
	}
	
	public void addNews(News news){
		this.newsList.add(news);
	}
	
	public void updateNews(int index, News news){
		this.newsList.set(index, news);
	}
	
	public void removeNews(int index){
		this.newsList.remove(index);
	}
	
	public void setNewsList(List<News> newsList){
		this.newsList=newsList;
	}
	
	public List<News> getNewsList(){
		return this.newsList;
	}
	
	//------------------------------------------------------
	
	//---------------------------------------Methods for Advertising
	
	public Advertising getAdvertising(int index){
		return this.advertisingList.get(index);
	}
	
	public void addAdvertising(Advertising advertising){
		this.advertisingList.add(advertising);
	}
	
	public void updateAdvertising(int index, Advertising advertising){
		this.advertisingList.set(index, advertising);
	}
	
	public void removeAdvertising(int index){
		this.advertisingList.remove(index);
	}
	
	public void setAdvertisingList(List<Advertising> advertisingList){
		this.advertisingList=advertisingList;
	}
	
	public List<Advertising> getAdvertisingList(){
		return this.advertisingList;
	}
	
	//------------------------------------------------------	
	
	//---------------------------------------Methods for Record
	
	public Record getRecord(int index){
		return this.recordList.get(index);
	}
	
	public void addRecord(Record record){
		this.recordList.add(record);
	}
	
	public void updateRecord(int index, Record record){
		this.recordList.set(index, record);
	}
	
	public void removeRecord(int index){
		this.recordList.remove(index);
	}
	
	public void setRecordList(List<Record> recordList){
		this.recordList=recordList;
	}
	
	public List<Record> getRecordList(){
		return this.recordList;
	}
	
	public List<Record> getSortedRecordList(){
		List<Record> sortedList=this.recordList;
		Collections.sort(sortedList);
		return sortedList;
	}
	
	//------------------------------------------------------	
	
//---------------------------------------Methods for Auction
	
	public Auction getAuction(int index){
		return this.auctionList.get(index);
	}
	
	public void addAuction(Auction auction){
		this.auctionList.add(auction);
	}
	
	public void updateAuction(int index, Auction auction){
		this.auctionList.set(index, auction);
	}
	
	public void removeAuction(int index){
		this.auctionList.remove(index);
	}
	
	
	public void setAuctionList(List<Auction> auctionList){
		this.auctionList=auctionList;
	}
	
	public List<Auction> getAuctionList(){
		return this.auctionList;
	}
	
	//------------------------------------------------------
	
	//------------------------------------------------Depredated

	public void setCash(int cash){
		this.cash=cash;
	}
	
	public int getCash(){
		return this.cash;
	}
	
	public void setDeposit(int deposit){
		this.deposit=deposit;
	}
	
	public int getDeposit(){
		return this.deposit;
	}
	
	public void setDebt(int debt){
		this.debt=debt;
	}
	
	public int getDebt(){
		return this.debt;
	}
	
	public void setHealth(int health){
		this.health=health;
	}
	
	public int getHealth(){
		return this.health;
	}
	
	public void setReputation(int reputation){
		this.reputation=reputation;
	}
	
	public int getReputation(){
		return this.reputation;
	}
	
	public void setTTL(int ttl){
		this.ttl=ttl;
	}
	
	public int getTTL(){
		return this.ttl;
	}
//----------------------------------------------------------
	
	public void setLocationId(int locationId){
		this.locationId=locationId;
	}
	
	public int getLocationId(){
		return this.locationId;
	}
	

	
	public void setUserId(int userId){
		this.userId=userId;
	}
	
	public int getUserId(){
		return this.userId;
	}
	
	public void setIsNewUser(int isNewUser){
		this.isNewUser=isNewUser;
	}
	
	public int getIsNewUser(){
		return this.isNewUser;
	}
	
	public void setSessionKey(String sessionKey){
		this.sessionKey=sessionKey;
	}
	
	public void setAPIKey(String API_Key){
		this.API_Key=API_Key;
	}
	
	public void setSECRETKey(String SECRET_Key){
		this.SECRET_Key=SECRET_Key;
	}
	
	public String getSessionKey(){
		return this.sessionKey;
	}
	
	public String getAPIKey(){
		return this.API_Key;
	}
	
	public String getSECRETKey(){
		return this.SECRET_Key;
	}
	
	
	public String getIsRefresh(){
		return this.isRefresh;
	}
	
	public void setIsRefresh(String isRefresh){
		this.isRefresh=isRefresh;
	}
	
}
