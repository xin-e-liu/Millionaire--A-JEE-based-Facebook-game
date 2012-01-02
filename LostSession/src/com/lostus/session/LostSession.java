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
import java.util.Map;

import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.w3c.dom.Document;

import com.lostus.ejb.user.User;
import com.lostus.ejb.user.UserFacadeRemote;
import com.lostus.ejb.shopping.*;
import com.lostus.ejb.advertising.*;
import com.lostus.ejb.commodity.*;
import com.lostus.ejb.usercart.*;
import com.lostus.ejb.location.*;
import com.lostus.ejb.event.*;
import com.lostus.ejb.news.*;
import com.lostus.ejb.record.*;
import com.lostus.ejb.auction.*;

import java.lang.StringBuffer;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Stateful
public class LostSession implements Serializable, LostSessionRemote {

    private static final long serialVersionUID = 1L;
    private int data = 0;

    private static final int RANDOM_SIZE = 5;

    private UserFacadeRemote userBean = null;

    private ShoppingFacadeRemote shoppingBean = null;

    private CommodityFacadeRemote commodityBean = null;

    private UserCartFacadeRemote userCartBean = null;

    private LocationFacadeRemote locationBean = null;

    private EventFacadeRemote eventBean = null;

    private NewsFacadeRemote newsBean = null;

    private GamingStateBeanRemote gsbr = null;

    private AdvertisingFacadeRemote advertisingBean = null;

    private RecordFacadeRemote recordBean = null;

    private AuctionFacadeRemote auctionBean = null;

    private Integer userMaxLife = 100;

    public int getData() {
        return this.data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void getDBConn() {

        // Setup Entity Bean connection.
        this.userBean = getUserConn();

        this.shoppingBean = getShoppingConn();

        this.commodityBean = getCommodityConn();

        this.userCartBean = getUserCartConn();

        this.locationBean = getLoationConn();

        this.eventBean = getEventConn();

        this.newsBean = getNewsConn();

        this.advertisingBean = getAdvertisingConn();

        this.recordBean = getRecordConn();

        this.auctionBean = getAuctionConn();

        // Initialize Gaming State
        try {
            InitialContext ctx = new InitialContext();
            gsbr = (GamingStateBeanRemote) ctx
                    .lookup("com.lostus.session.GamingStateBeanRemote");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // -------------------------------------------------------------Entity Bean
    // Connections

    public UserFacadeRemote getUserConn() {
        try {
            InitialContext ctx = new InitialContext();
            UserFacadeRemote bean = (UserFacadeRemote) ctx
                    .lookup("com.lostus.ejb.user.UserFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ShoppingFacadeRemote getShoppingConn() {
        try {
            InitialContext ctx = new InitialContext();
            ShoppingFacadeRemote bean = (ShoppingFacadeRemote) ctx
                    .lookup("com.lostus.ejb.shopping.ShoppingFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public CommodityFacadeRemote getCommodityConn() {
        try {
            InitialContext ctx = new InitialContext();
            CommodityFacadeRemote bean = (CommodityFacadeRemote) ctx
                    .lookup("com.lostus.ejb.commodity.CommodityFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserCartFacadeRemote getUserCartConn() {
        try {
            InitialContext ctx = new InitialContext();
            UserCartFacadeRemote bean = (UserCartFacadeRemote) ctx
                    .lookup("com.lostus.ejb.usercart.UserCartFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public LocationFacadeRemote getLoationConn() {
        try {
            InitialContext ctx = new InitialContext();
            LocationFacadeRemote bean = (LocationFacadeRemote) ctx
                    .lookup("com.lostus.ejb.location.LocationFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public EventFacadeRemote getEventConn() {
        try {
            InitialContext ctx = new InitialContext();
            EventFacadeRemote bean = (EventFacadeRemote) ctx
                    .lookup("com.lostus.ejb.event.EventFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public NewsFacadeRemote getNewsConn() {
        try {
            InitialContext ctx = new InitialContext();
            NewsFacadeRemote bean = (NewsFacadeRemote) ctx
                    .lookup("com.lostus.ejb.news.NewsFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AdvertisingFacadeRemote getAdvertisingConn() {
        try {
            InitialContext ctx = new InitialContext();
            AdvertisingFacadeRemote bean = (AdvertisingFacadeRemote) ctx
                    .lookup("com.lostus.ejb.advertising.AdvertisingFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public RecordFacadeRemote getRecordConn() {
        try {
            InitialContext ctx = new InitialContext();
            RecordFacadeRemote bean = (RecordFacadeRemote) ctx
                    .lookup("com.lostus.ejb.record.RecordFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public AuctionFacadeRemote getAuctionConn() {
        try {
            InitialContext ctx = new InitialContext();
            AuctionFacadeRemote bean = (AuctionFacadeRemote) ctx
                    .lookup("com.lostus.ejb.auction.AuctionFacadeRemote");
            return bean;
        } catch (NamingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // --------------------------------------------------------------Entity Bean
    // and Gaming State Operations

    public int isUserExist(int userId) {
        User u = this.userBean.findById(userId);
        if (u == null)
            return 0;
        else
            return 1;
    }

    public void addNewUser(int userId, String name) {
        User u = new User(userId);
        u.setUname(name);
        this.userBean.save(u);
    }

    public void resetUser(int userId) {
        User u = this.userBean.findById(userId);
        u.setUcapacity(100);
        u.setUdebt(0);
        u.setUdeposit(0);
        u.setUhealth(100);
        u.setUmoney(2000);
        u.setUreputation(100);
        u.setUttl(userMaxLife);
        this.userBean.update(u);
    }

    public void resetUserGS(int userId) {
        User u = new User();
        u.setUid(userId);
        u.setUname(this.gsbr.getUser().getUname());
        u.setUcapacity(100);
        u.setUdebt(0);
        u.setUdeposit(0);
        u.setUhealth(100);
        u.setUmoney(2000);
        u.setUreputation(100);
        u.setUttl(userMaxLife);
        u.setSessionId(this.gsbr.getSessionKey());
        this.gsbr.setUser(u);
    }

    public void setUserSessionId(int userId, String sessionId) {
        User u = this.userBean.findById(userId);
        u.setSessionId(sessionId);
        this.userBean.update(u);
    }

    public void setUserSessionIdGS(int userId, String sessionId) {
        User u = this.gsbr.getUser();
        u.setSessionId(sessionId);
        this.gsbr.setUser(u);
    }

    public int updateUserMoney(int userId, int userCash, int isBuy) {
        User u = this.userBean.findById(userId);
        int availMoney = u.getUmoney();
        if (isBuy == 1) {
            if (userCash > availMoney) {
                userCash = availMoney;
            }
            u.setUmoney(availMoney - userCash);
            this.userBean.update(u);
            return userCash;
        } else {
            u.setUmoney(availMoney + userCash);
            this.userBean.update(u);
            return userCash;
        }
    }

    public int updateUserMoneyGS(int userId, int userCash, int isBuy) {
        User u = this.gsbr.getUser();
        int availMoney = u.getUmoney();
        if (isBuy == 1) {
            if (userCash > availMoney) {
                userCash = availMoney;
            }
            u.setUmoney(availMoney - userCash);
            this.gsbr.setUser(u);
            return userCash;
        } else {
            u.setUmoney(availMoney + userCash);
            this.gsbr.setUser(u);
            return userCash;
        }
    }

    public int updateUserHealthGS(int userId, int userHealth, int isDecrease) {
        User u = this.gsbr.getUser();
        int availHealth = u.getUhealth();
        if (isDecrease == 1) {
            if (userHealth > availHealth) {
                userHealth = availHealth;
            }
            u.setUhealth(availHealth - userHealth);
            this.gsbr.setUser(u);
            return userHealth;
        } else {
            u.setUhealth(availHealth + userHealth);
            this.gsbr.setUser(u);
            return userHealth;
        }
    }

    public int updateUserReputationGS(int userId, int userReputation,
            int isDecrease) {
        User u = this.gsbr.getUser();
        int availReputation = u.getUreputation();
        if (isDecrease == 1) {
            if (userReputation > availReputation) {
                userReputation = availReputation;
            }
            u.setUreputation(availReputation - userReputation);
            this.gsbr.setUser(u);
            return userReputation;
        } else {
            u.setUreputation(availReputation + userReputation);
            this.gsbr.setUser(u);
            return userReputation;
        }
    }

    public int updateUserDeposit(int userId, int userCash, int isDeposit) {
        User u = this.userBean.findById(userId);
        int availDeposit = u.getUdeposit();
        if (isDeposit == 0) {
            if (userCash > availDeposit) {
                userCash = availDeposit;
            }
            u.setUdeposit(availDeposit - userCash);
            this.userBean.update(u);
            return userCash;
        } else {
            u.setUdeposit(availDeposit + userCash);
            this.userBean.update(u);
            return userCash;
        }
    }

    public int updateUserDepositGS(int userId, int userCash, int isDeposit) {
        User u = this.gsbr.getUser();
        int availDeposit = u.getUdeposit();
        if (isDeposit == 0) {
            if (userCash > availDeposit) {
                userCash = availDeposit;
            }
            u.setUdeposit(availDeposit - userCash);
            this.gsbr.setUser(u);
            return userCash;
        } else {
            u.setUdeposit(availDeposit + userCash);
            this.gsbr.setUser(u);
            return userCash;
        }
    }

    public void updateUserTTL(int userId, int TTL) {
        User u = this.userBean.findById(userId);
        int availTTL = u.getUttl();
        availTTL = availTTL - TTL;
        u.setUttl(availTTL);
        this.userBean.update(u);
    }

    public void updateUserTTLGS(int userId, int TTL) {
        User u = this.gsbr.getUser();
        int availTTL = u.getUttl();
        availTTL = availTTL - TTL;
        u.setUttl(availTTL);
        this.gsbr.setUser(u);
    }

    public void updateUserCapacity(int userId, int quantity, int isBuy) {
        User u = this.userBean.findById(userId);
        int availCapacity = u.getUcapacity();
        if (isBuy == 1) {
            if (quantity > availCapacity) {
                quantity = availCapacity;
            }
            u.setUcapacity(availCapacity - quantity);
            this.userBean.update(u);
        } else {
            if (availCapacity + quantity > 100) {
                availCapacity = 100;
            }
            u.setUcapacity(availCapacity + quantity);
            this.userBean.update(u);
        }
    }

    public void updateUserCapacityGS(int userId, int quantity, int isBuy) {
        User u = this.gsbr.getUser();
        int availCapacity = u.getUcapacity();
        if (isBuy == 1) {
            if (quantity > availCapacity) {
                quantity = availCapacity;
            }
            u.setUcapacity(availCapacity - quantity);
            this.gsbr.setUser(u);
        } else {
            if (availCapacity + quantity > 100) {
                availCapacity = 100;
            }
            u.setUcapacity(availCapacity + quantity);
            this.gsbr.setUser(u);
        }
    }

    public String getUserNameByUserIdGS(int userId) {
        User u = this.getUserByIdGS(userId);
        if (u == null) {
            return "NA";
        } else {
            return u.getUname();
        }
    }

    public List<Shopping> getMarketList(int userId) {
        return this.shoppingBean.findByUid(userId, 0);
    }

    public List<Shopping> getMarketListGS(int userId) {
        return this.gsbr.getShoppingList();
    }

    public List<Shopping> getMarketListByuIdlId(int userId, int locationId) {
        List<Shopping> a = this.shoppingBean.findByUid(userId, 0);
        ArrayList<Shopping> r = new ArrayList<Shopping>();

        for (int t = 0; t < a.size(); t++) {
            Shopping s = (Shopping) a.get(t);
            if (s.getLid().intValue() == locationId) {
                r.add(s);
            }
        }
        return r;
    }

    public List<Shopping> getMarketListByuIdlIdGS(int userId, int locationId) {
        List<Shopping> a = this.gsbr.getShoppingList();
        ArrayList<Shopping> r = new ArrayList<Shopping>();

        for (int t = 0; t < a.size(); t++) {
            Shopping s = (Shopping) a.get(t);
            if (s.getLid().intValue() == locationId) {
                r.add(s);
            }
        }
        return r;
    }

    public void updateCommodityQuantity(int userId, int cId, int cQuantity) {
        ShoppingId sI = new ShoppingId(userId, cId);
        Shopping s = this.shoppingBean.findById(sI);
        // s.setCquantity(s.getCquantity()-cQuantity);
        this.shoppingBean.update(s);
    }

    public void updateCommodityQuantityGS(int userId, int cId, int cQuantity) {
        List<Shopping> sList = this.gsbr.getShoppingList();
        for (int t = 0; t < sList.size(); t++) {
            if (sList.get(t).getId().getCid().intValue() == cId) {
                sList.get(t).setCquantity(cQuantity);
                this.gsbr.updateShopping(t, sList.get(t));
            }
        }
        // s.setCquantity(s.getCquantity()-cQuantity);
        // this.gsbr.setShoppingList(sList);
    }

    public void updateCommoditylId(int userId, int lId) {
        List<Shopping> a = this.shoppingBean.findByUid(userId, 0);
        if (a.size() == 0) {
            return;
        }

        for (int t = 0; t < a.size(); t++) {
            a.get(t).setLid(0);
            this.shoppingBean.update(a.get(t));
        }

        Random rand = new Random();

        for (int t = 0; t < RANDOM_SIZE; t++) {
            int randomInt = rand.nextInt(a.size());
            a.get(randomInt).setLid(lId);
            this.shoppingBean.update(a.get(randomInt));
        }
    }

    public void updateCommoditylIdGS(int userId, int lId) {
        List<Shopping> a = this.gsbr.getShoppingList();
        if (a.size() == 0) {
            return;
        }

        for (int t = 0; t < a.size(); t++) {
            a.get(t).setLid(0);
        }

        Random rand = new Random();

        for (int t = 0; t < RANDOM_SIZE; t++) {
            int randomInt = rand.nextInt(a.size());
            a.get(randomInt).setLid(lId);
        }
        this.gsbr.setShoppingList(a);
    }

    public int getUserIdBySessionId(String sessionId) {
        return this.userBean.findBySessionId(sessionId, 0).get(0).getUid();
    }

    public int getUserIdBySessionIdGS(String sessionId) {
        return this.gsbr.getUser().getUid();
    }

    public String getSessionIdByUserId(int userId) {
        User u = this.userBean.findById(userId);
        return u.getSessionId();
    }

    public String getSessionIdByUserIdGS(int userId) {
        User u = this.gsbr.getUser();
        return u.getSessionId();
    }

    public List<UserCart> getCartList(int userId) {
        return this.userCartBean.findByUid(userId, 0);
    }

    public List<UserCart> getCartListGS(int userId) {
        return this.gsbr.getCartList();
    }

    public void resetCart(int userId) {
        List<UserCart> c = this.userCartBean.findByUid(userId, 0);
        for (int t = 0; t < c.size(); t++) {
            this.userCartBean.delete(c.get(t));
        }
    }

    public void resetCartGS(int userId) {
        List<UserCart> c = new ArrayList<UserCart>();
        this.gsbr.setCartList(c);
    }

    public List<Commodity> getCommodity() {
        return this.commodityBean.findAll(0);
    }

    public List<Commodity> getCommodityGS() {
        return this.gsbr.getCommodityList();
    }

    public void resetMarket(int userId, int locationId) {

        // Remove everything from Shopping with userId
        List<Shopping> a = this.shoppingBean.findByUid(userId, 0);
        for (int t = 0; t < a.size(); t++) {
            this.shoppingBean.delete(a.get(t));
        }
        // Import all commodities from Commodity->Shopping
        List<Commodity> c = this.commodityBean.findAll(0);

        for (int t = 0; t < c.size(); t++) {
            Shopping s = new Shopping();
            ShoppingId sId = new ShoppingId();
            sId.setUid(userId);
            sId.setCid(c.get(t).getCid());
            s.setId(sId);
            s.setLid(0);
            s.setCprice(10);
            s.setCquantity(10);
            this.shoppingBean.save(s);
        }

        a = this.shoppingBean.findByUid(userId, 0);
        Random rand = new Random();

        // Set some commodity to the initial location identified by locationId
        for (int t = 0; t < RANDOM_SIZE; t++) {
            int randomInt = rand.nextInt(a.size());
            a.get(randomInt).setLid(locationId);
            this.shoppingBean.update(a.get(randomInt));
        }
    }

    public void resetMarketGS(int userId, int locationId) {

        // Remove everything from Shopping with userId
        List<Shopping> a = new ArrayList<Shopping>();
        ;

        // Import all commodities from Commodity->Shopping
        List<Commodity> c = this.gsbr.getCommodityList();
        Random rPrice = new Random();
        int price = 0;
        for (int t = 0; t < c.size(); t++) {
            Shopping s = new Shopping();
            ShoppingId sId = new ShoppingId();
            sId.setUid(userId);
            sId.setCid(c.get(t).getCid());
            s.setId(sId);
            s.setLid(0);

            // Reset the price of the item between the min price and max price
            price = rPrice.nextInt(c.get(t).getCmaxPrice()
                    - c.get(t).getCminPrice())
                    + c.get(t).getCminPrice();
            s.setCprice(price);
            s.setCquantity(10);
            a.add(s);
        }
        this.gsbr.setShoppingList(a);

        Random rand = new Random();

        // Set some commodity to the initial location identified by locationId
        for (int t = 0; t < RANDOM_SIZE; t++) {
            int randomInt = rand.nextInt(a.size());
            a.get(randomInt).setLid(locationId);
            this.gsbr.updateShopping(randomInt, a.get(randomInt));
        }
        // this.gsbr.setShoppingList(a);
        // System.out.println("&&&&&&&&&&&&&"+this.gsbr.getShoppingList().toString());
    }

    public void cleanAuction(int userId) {

        List<Auction> aa = this.auctionBean.findByUid(userId, 0);
        for (int t = 0; t < aa.size(); t++) {
            aa.get(t).setTimeLeft(0);
            this.auctionBean.update(aa.get(t));
        }

        List<Auction> ab = this.auctionBean.findByBidUid(userId, 0);
        for (int t = 0; t < ab.size(); t++) {
            ab.get(t).setBidPrice(0);
            ab.get(t).setIsMaxBid(0);
            this.auctionBean.update(ab.get(t));
        }
    }

    public void updateCart(int userId, int cId, int cQuantity, int cPrice) {
        UserCartId ucId = new UserCartId(userId, cId, cPrice);
        UserCart uC = this.userCartBean.findById(ucId);
        if (uC == null) {
            uC = new UserCart(ucId, cQuantity);
            this.userCartBean.save(uC);
            return;
        }
        uC.setQuantity(uC.getQuantity() + cQuantity);
        this.userCartBean.update(uC);
    }

    public void updateCartGS(int userId, int cId, int cQuantity, int cPrice) {

        UserCartId ucId = new UserCartId(userId, cId, cPrice);
        UserCart uC = null;

        List<UserCart> ucList = this.gsbr.getCartList();

        int index = 0;
        for (int t = 0; t < ucList.size(); t++) {
            if (ucList.get(t).getId().getCid().intValue() == cId
                    && ucList.get(t).getId().getPriceBought().intValue() == cPrice) {
                uC = ucList.get(t);
                index = t;
            }
        }

        if (uC == null) {
            uC = new UserCart(ucId, cQuantity);
            this.gsbr.addCartt(uC);
            return;
        } else {

            uC.setQuantity(uC.getQuantity() + cQuantity);

            this.gsbr.updateCart(index, uC);
        }
    }

    public void updateCartSell(int userId, int cId, int cQuantity, int cPrice) {
        UserCartId ucId = new UserCartId(userId, cId, cPrice);
        UserCart uC = this.userCartBean.findById(ucId);
        if (uC == null) {
            return;
        }
        int preQuantity = uC.getQuantity();
        if (preQuantity < cQuantity) {
            cQuantity = preQuantity;
        }
        uC.setQuantity(preQuantity - cQuantity);
        this.userCartBean.update(uC);

        if (uC.getQuantity().intValue() == 0) {
            this.userCartBean.delete(uC);
        }
    }

    public void updateCartSellGS(int userId, int cId, int cQuantity, int cPrice) {

        UserCartId ucId = new UserCartId(userId, cId, cPrice);
        UserCart uC = null;

        List<UserCart> ucList = this.gsbr.getCartList();
        int index = 0;
        for (int t = 0; t < ucList.size(); t++) {
            if (ucList.get(t).getId().getCid().intValue() == cId
                    && ucList.get(t).getId().getPriceBought().intValue() == cPrice) {
                uC = ucList.get(t);
                index = t;
            }
        }
        if (uC == null) {
            return;
        }
        int preQuantity = uC.getQuantity();
        if (preQuantity < cQuantity) {
            cQuantity = preQuantity;
        }
        uC.setQuantity(preQuantity - cQuantity);
        this.gsbr.updateCart(index, uC);

        if (uC.getQuantity().intValue() == 0) {
            this.gsbr.removeCart(index);
        }
    }

    public void updateCartAuctionGS(int userId, int cId, int cQuantity,
            int cOriginalPrice) {

        UserCartId ucId = new UserCartId(userId, cId, cOriginalPrice);
        UserCart uC = null;

        List<UserCart> ucList = this.gsbr.getCartList();
        int index = 0;
        for (int t = 0; t < ucList.size(); t++) {
            if (ucList.get(t).getId().getCid().intValue() == cId
                    && ucList.get(t).getId().getPriceBought() == cOriginalPrice) {
                uC = ucList.get(t);
                index = t;
            }
        }
        if (uC == null) {
            return;
        }
        int preQuantity = uC.getQuantity();
        if (preQuantity < cQuantity) {
            cQuantity = preQuantity;
        }
        uC.setQuantity(preQuantity - cQuantity);
        // uC.setPriceBought(cPrice);
        this.gsbr.updateCart(index, uC);

        if (uC.getQuantity().intValue() == 0) {
            this.gsbr.removeCart(index);
        }
    }

    public String getCNameByCId(int cId) {
        Commodity c = this.commodityBean.findById(cId);
        return c.getCname();
    }

    public String getCNameByCIdGS(int cId) {
        Commodity c = null;
        List<Commodity> cList = this.gsbr.getCommodityList();
        for (int t = 0; t < cList.size(); t++) {
            if (cList.get(t).getCid().intValue() == cId) {
                c = cList.get(t);
            }
        }
        return c.getCname();
    }

    public User getUserById(int uId) {
        return this.userBean.findById(uId);
    }

    public User getCurrentUserGS() {
        return this.gsbr.getUser();
    }

    public User getUserByIdGS(int uId) {

        System.out.println(uId);

        User u = null;
        List<User> aul = this.gsbr.getAllUserList();
        System.out.println(aul);

        for (int t = 0; t < aul.size(); t++) {
            u = aul.get(t);
            if (u.getUid().intValue() == uId) {
                return u;
            }
        }
        return u;
    }

    public List<User> getAllUser() {
        return this.userBean.findAll(0);
    }

    public List<Location> getLocationByType(int lType) {
        return this.locationBean.findByLtype(lType, 0);
    }

    public List<Location> getLocationByTypeGS(int lType) {

        List<Location> rLList = new ArrayList<Location>();
        List<Location> lList = this.gsbr.getLocationList();
        for (int t = 0; t < lList.size(); t++) {
            if (lList.get(t).getLtype().intValue() == lType) {
                rLList.add(lList.get(t));
            }
        }
        return rLList;
    }

    public List<Location> getLocation() {
        return this.locationBean.findAll(0);
    }

    public List<Location> getLocationGS() {
        return this.gsbr.getLocationList();
    }

    public Event getEvent(int function, int code) {
        List<Event> e = this.eventBean.findByEfunc(function, 0);
        Event ev = null;
        for (int t = 0; t < e.size(); t++) {
            if (e.get(t).getEcode().intValue() == code) {
                ev = e.get(t);
                break;
            }
        }
        return ev;
    }

    public Event getEventGS(int function, int code) {
        List<Event> e = this.gsbr.getEventList();
        List<Event> eFuncList = new ArrayList<Event>();
        for (int t = 0; t < e.size(); t++) {
            if (e.get(t).getEfunc().intValue() == function) {
                eFuncList.add(e.get(t));
            }
        }
        Event ev = null;
        for (int t = 0; t < eFuncList.size(); t++) {
            if (eFuncList.get(t).getEcode().intValue() == code) {
                ev = eFuncList.get(t);
                break;
            }
        }
        return ev;
    }

    public List<Event> getEvent() {
        return this.eventBean.findAll(0);
    }

    public List<Event> getEventGS() {
        return this.gsbr.getEventList();
    }

    public void updateFirstNews(int userId, int priority, int type,
            String content, int property) {
        List<News> n = this.newsBean.findByUid(userId, 0);
        if (n.size() == 0) {
            // Add the news
            News news = new News(content, type, priority, userId, 1, property);
            this.newsBean.save(news);
            return;
        }
        News news = null;
        for (int t = 0; t < n.size(); t++) {
            if (n.get(t).getPriority().intValue() == priority) {
                news = n.get(t);
                break;
            }
        }
        if (news != null) {
            // update
            news.setContent(content);
            news.setPriority(priority);
            news.setType(type);
            news.setProperty(property);
            this.newsBean.update(news);
        } else {
            // add
            news = new News(content, type, priority, userId, 1, property);
            this.newsBean.save(news);
        }
        return;
    }

    public void updateFirstNewsGS(int userId, int priority, int type,
            String content, int property) {
        List<News> n = this.gsbr.getNewsList();
        if (n.size() == 0) {
            // Add the news
            News news = new News(content, type, priority, userId, 1, property);
            this.gsbr.addNews(news);
            return;
        }
        News news = null;
        int index = 0;
        for (int t = 0; t < n.size(); t++) {
            if (n.get(t).getPriority().intValue() == priority) {
                news = n.get(t);
                index = t;
                break;
            }
        }
        if (news != null) {
            // update
            news.setContent(content);
            news.setPriority(priority);
            news.setType(type);
            news.setProperty(property);
            this.gsbr.updateNews(index, news);
        } else {
            // add
            news = new News(content, type, priority, userId, 1, property);
            this.gsbr.addNews(news);
        }
        return;
    }

    public void updateNewsGS(int userId, int priority, int type,
            String content, int idInType, int property) {
        List<News> n = this.gsbr.getNewsList();
        if (n.size() == 0) {
            // Add the news
            News news = new News(content, type, priority, userId, idInType,
                    property);
            this.gsbr.addNews(news);
            return;
        }
        News news = null;
        int index = 0;
        for (int t = 0; t < n.size(); t++) {
            if (n.get(t).getPriority().intValue() == priority) {
                news = n.get(t);
                index = t;
                break;
            }
        }
        if (news != null) {
            // update
            news.setContent(content);
            news.setPriority(priority);
            news.setType(type);
            news.setProperty(property);
            this.gsbr.updateNews(index, news);
        } else {
            // add
            news = new News(content, type, priority, userId, idInType, property);
            this.gsbr.addNews(news);
        }
        return;
    }

    public List<News> getNewsListByUid(int userId) {
        return this.newsBean.findByUid(userId, 0);
    }

    public List<News> getNewsListByUidGS(int userId) {
        return this.gsbr.getNewsList();
    }

    public GamingStateBeanRemote getGS() {
        return this.gsbr;
    }

    public Advertising getAdvertisingByID(int adtype, int adid, int property) {
        AdvertisingId aid = new AdvertisingId(adtype, adid, property);
        return this.advertisingBean.findById(aid);
    }

    public Advertising getAdvertisingByIDGS(int adtype, int adid, int property) {

        List<Advertising> aList = this.gsbr.getAdvertisingList();
        Advertising a = null;
        for (int t = 0; t < aList.size(); t++) {
            if (aList.get(t).getId().getAdtype().intValue() == adtype
                    && aList.get(t).getId().getAdid().intValue() == adid
                    && aList.get(t).getId().getProperty().intValue() == property) {
                a = aList.get(t);
            }
        }
        return a;
    }

    public List<Advertising> getAllAdvertising() {
        return this.advertisingBean.findAll(0);
    }

    public List<Advertising> getAdvertisingByAdType(int adtype) {
        return this.advertisingBean.findByAdType(adtype, 0);
    }

    public List<Advertising> getAdvertisingByAdTypeGS(int adtype) {
        List<Advertising> aList = this.gsbr.getAdvertisingList();
        List<Advertising> a = new ArrayList<Advertising>();
        for (int t = 0; t < aList.size(); t++) {
            if (aList.get(t).getId().getAdtype().intValue() == adtype) {
                a.add(aList.get(t));
            }
        }
        return a;
    }

    public List<Record> getAllRecord() {
        return this.getRecordConn().findAll(0);
    }

    public List<Record> getAllRecordGS() {
        return this.gsbr.getRecordList();
    }

    public List<Record> getSortedRecordGS() {
        return this.gsbr.getSortedRecordList();
    }

    public void addRecord(int userId, String uname, int cash, int deposit,
            int health, int reputation, int debt, Date timestamp) {
        Record r = new Record();
        r.setUname(uname);
        r.setUcash(cash);
        r.setUdeposit(deposit);
        r.setUid(userId);
        r.setUhealth(health);
        r.setUreputation(reputation);
        r.setUdebt(debt);
        r.setRtimestamp(timestamp);

        this.getRecordConn().save(r);
    }

    public void addRecordGS(int userId, String uname, int cash, int deposit,
            int health, int reputation, int debt, Date timestamp) {
        Record r = new Record();
        r.setUname(uname);
        r.setUcash(cash);
        r.setUdeposit(deposit);
        r.setUid(userId);
        r.setUhealth(health);
        r.setUreputation(reputation);
        r.setUdebt(debt);
        r.setRtimestamp(timestamp);

        this.gsbr.addRecord(r);
    }

    public List<Auction> getOnGoAuction() {
        return this.auctionBean.findByBidUid(0, 0);
    }

    public List<Auction> getOnGoAuctionGS() {

        List<Auction> allAuction = this.gsbr.getAuctionList();
        List<Auction> onGoAuction = new ArrayList<Auction>();

        for (int t = 0; t < allAuction.size(); t++) {
            if (allAuction.get(t).getId().getBidUid().intValue() == 0) {
                onGoAuction.add(allAuction.get(t));
            }
        }
        return onGoAuction;
    }

    public List<Auction> getYourBids(int userId) {
        return this.auctionBean.findByBidUid(userId, 0);
    }

    public List<Auction> getYourBidsGS(int userId) {

        List<Auction> allAuction = this.gsbr.getAuctionList();
        List<Auction> yourBids = new ArrayList<Auction>();

        for (int t = 0; t < allAuction.size(); t++) {
            if (allAuction.get(t).getId().getBidUid().intValue() == userId) {
                yourBids.add(allAuction.get(t));
            }
        }
        return yourBids;
    }

    public List<Auction> getYourAuction(int userId) {

        List<Auction> onGoAuction = this.getOnGoAuction();
        List<Auction> yourAuction = new ArrayList<Auction>();

        for (int t = 0; t < onGoAuction.size(); t++) {
            if (onGoAuction.get(t).getId().getUid().intValue() == userId
                    && onGoAuction.get(t).getId().getBidUid().intValue() == 0) {
                yourAuction.add(onGoAuction.get(t));
            }
        }

        return yourAuction;
    }

    public List<Auction> getYourAuctionGS(int userId) {

        List<Auction> allAuction = this.gsbr.getAuctionList();
        List<Auction> yourAuction = new ArrayList<Auction>();

        for (int t = 0; t < allAuction.size(); t++) {
            if (allAuction.get(t).getId().getUid().intValue() == userId
                    && allAuction.get(t).getId().getBidUid().intValue() == 0) {
                yourAuction.add(allAuction.get(t));
            }
        }
        return yourAuction;
    }

    public void makeYourBid(int cId, int uId, int bidUserId, int bidPrice,
            int bidTime) {

        // First check if the user bid the cId before
        AuctionId aId = new AuctionId();
        aId.setCid(cId);
        aId.setUid(uId);
        aId.setBidUid(bidUserId);
        Auction a = this.auctionBean.findById(aId);
        if (a == null) {
            // No previous bid. Add a new bid

            // The new bid is the max price bid
            List<Auction> maxPriceAuction = this.auctionBean.findByIsMaxBid(
                    1,
                    0);
            Auction maxA = new Auction();
            for (int t = 0; t < maxPriceAuction.size(); t++) {
                if (maxPriceAuction.get(t).getId().getCid().intValue() == cId
                        && maxPriceAuction.get(t).getId().getUid().intValue() == uId) {
                    maxA = maxPriceAuction.get(t);
                }
            }
            if (maxA.getBidPrice() > bidPrice) {
                return;
            }

            maxA.setIsMaxBid(0);
            this.auctionBean.update(maxA);

            aId.setCid(cId);
            aId.setUid(uId);
            aId.setBidUid(0);

            a = this.auctionBean.findById(aId);

            aId.setBidUid(bidUserId);

            a.setId(aId);
            a.setBidPrice(bidPrice);
            a.setBidTime(bidTime);
            a.setIsMaxBid(1);
            this.auctionBean.save(a);

            // Update cash and capacity
            int total = (new Integer(a.getBidPrice()))
                    * (new Integer(a.getQuantity()));
            this.updateUserMoneyGS(new Integer(bidUserId), total, 1);
            this.updateUserCapacityGS(
                    new Integer(bidUserId),
                    new Integer(a.getQuantity()),
                    1);

        } else {
            // Already bid before. Update the bid.

            // The new bid is the max price bid
            List<Auction> maxPriceAuction = this.auctionBean.findByIsMaxBid(
                    1,
                    0);
            Auction maxA = new Auction();
            for (int t = 0; t < maxPriceAuction.size(); t++) {
                if (maxPriceAuction.get(t).getId().getCid().intValue() == cId
                        && maxPriceAuction.get(t).getId().getUid().intValue() == uId) {
                    maxA = maxPriceAuction.get(t);
                }
            }
            if (maxA.getBidPrice() > bidPrice) {
                return;
            }

            maxA.setIsMaxBid(0);
            this.auctionBean.update(maxA);

            int priceDiff = bidPrice - a.getBidPrice();

            a.setBidPrice(bidPrice);
            a.setBidTime(bidTime);
            a.setIsMaxBid(1);
            this.auctionBean.update(a);

            // Update cash
            int total = (new Integer(priceDiff))
                    * (new Integer(a.getQuantity()));
            this.updateUserMoneyGS(new Integer(bidUserId), total, 1);
        }

    }

    public void makeYourAuction(int cId, int uId, int originalPrice,
            int unitPrice, int quantity, int ttl) {

        // Check if the user has put the cId in the auction house before.
        AuctionId aId = new AuctionId();
        aId.setBidUid(0);
        aId.setCid(cId);
        aId.setUid(uId);
        Auction a = this.auctionBean.findById(aId);
        if (a != null) {
            return;
        }
        a = new Auction();
        a.setId(aId);
        a.setUnitPrice(unitPrice);
        a.setQuantity(quantity);
        a.setTimeLeft(ttl);
        a.setLid(0);
        a.setAuctionTime(ttl);
        a.setBidPrice(unitPrice);
        a.setIsMaxBid(1);
        a.setOriginalPrice(originalPrice);
        this.auctionBean.save(a);

        // Update cart information
        this.updateCartAuctionGS(
                new Integer(uId),
                new Integer(cId),
                new Integer(quantity),
                originalPrice);
    }

    public List<Auction> getOnGoAuctionWithMaxBidPrice() {
        return this.getAuctionConn().findByIsMaxBid(1, 0);
    }

    public List<Integer> getMaxBidPrices(List<Integer> cIds, List<Integer> uIds) {
        List<Auction> allMaxPriceBids = this.auctionBean.findByIsMaxBid(1, 0);
        List<Integer> maxPrices = new ArrayList<Integer>();

        for (int tt = 0; tt < cIds.size(); tt++) {
            for (int t = 0; t < allMaxPriceBids.size(); t++) {

                if ((allMaxPriceBids.get(t).getId().getCid().intValue() == cIds
                        .get(tt).intValue())
                        && (allMaxPriceBids.get(t).getId().getUid().intValue() == uIds
                                .get(tt).intValue())) {
                    maxPrices.add(allMaxPriceBids.get(t).getBidPrice());
                }
            }
        }

        return maxPrices;
    }

    public void removeAuction(int cId, int uId, int bidUId) {
        AuctionId aId = new AuctionId();
        aId.setBidUid(bidUId);
        aId.setCid(cId);
        aId.setUid(uId);

        Auction a = this.getAuctionConn().findById(aId);
        this.getAuctionConn().delete(a);
    }

    public void updateAuctionTTL(int userId) {
        List<Auction> a = this.getAuctionConn().findByUid(userId, 0);

        for (int t = 0; t < a.size(); t++) {
            Auction tmp = a.get(t);
            if (tmp.getTimeLeft().intValue() == 0) {
                this.getAuctionConn().delete(tmp);
            }
            tmp.setTimeLeft(tmp.getTimeLeft() - 1);
            this.getAuctionConn().update(tmp);
        }
    }

    public void checkoutAuction(int userId) {

        System.out.println("Checkout all the auctions");
        List<Auction> a = this.getOnGoAuctionWithMaxBidPrice();
        List<Auction> checkedoutAuction = new ArrayList<Auction>();
        for (int t = 0; t < a.size(); t++) {
            // Check out auction
            if (a.get(t).getTimeLeft().intValue() == 0
                    && a.get(t).getId().getUid().intValue() == userId) {

                if (a.get(t).getId().getBidUid().intValue() == 0) {
                    // Auction failed
                    this.updateCartGS(
                            userId,
                            a.get(t).getId().getCid(),
                            a.get(t).getQuantity(),
                            a.get(t).getOriginalPrice());

                } else {

                    // These auction sales are checked out. Users who win bids
                    // should update this userCart and cash
                    checkedoutAuction.add(a.get(t));

                    // Current user update cart and cash

                    // Update user information
                    int total = (new Integer(a.get(t).getBidPrice()))
                            * (new Integer(a.get(t).getQuantity()));
                    this.updateUserMoneyGS(new Integer(userId), total, 0);
                    this.updateUserCapacityGS(new Integer(userId), new Integer(
                            a.get(t).getQuantity()), 0);
                }

            }
        }
    }

    public void checkoutBid(int userId) {

        System.out.println("Checkout all the bids");
        List<Auction> a = this.getAuctionConn().findByTimeLeft(0, 0);
        for (int t = 0; t < a.size(); t++) {
            if (a.get(t).getId().getBidUid().intValue() == userId) {
                if (a.get(t).getIsMaxBid().intValue() == 1) {
                    // Win the bid
                    this.updateCartGS(
                            new Integer(userId),
                            new Integer(a.get(t).getId().getCid()),
                            a.get(t).getQuantity(),
                            new Integer(a.get(t).getBidPrice()));
                } else {
                    // Lose the bid
                    int total = (new Integer(a.get(t).getBidPrice()))
                            * (new Integer(a.get(t).getQuantity()));
                    this.updateUserMoneyGS(new Integer(userId), total, 0);
                    this.updateUserCapacityGS(new Integer(userId), new Integer(
                            a.get(t).getQuantity()), 0);
                }
            }
        }
    }

    public String getRespMsg(Map params, Map headers, String inMsg) {
        String button1 = (String) params.get("button1");
        System.out.println(button1);
        StringBuffer respMsg = new StringBuffer();
        respMsg.append("<DIV ALIGN=CENTER>");
        respMsg.append("<fb:editor>");
        respMsg.append("<fb:editor-buttonset>");
        respMsg.append("<fb:editor-button value=\"NYC\" name=\"button1\"/>");
        respMsg.append("<fb:editor-button value=\"Buffalo\" name=\"button2\"/>");
        respMsg.append("<fb:editor-button value=\"Davis\" name=\"button3\"/>");
        respMsg.append("</fb:editor-buttonset>");
        respMsg.append("</fb:editor>");
        respMsg.append("</DIV>");
        respMsg.append("<DIV ALIGN=CENTER>");
        respMsg.append("<fb:editor>");
        respMsg.append("<fb:editor-buttonset>");
        respMsg.append("<fb:editor-button value=\"Las Vegas\" name=\"button4\"/>");
        respMsg.append("<fb:editor-button value=\"Seattle\" name=\"button5\"/>");
        respMsg.append("<fb:editor-button value=\"SFO\" name=\"button6\"/>");

        respMsg.append("</fb:editor-buttonset>");
        respMsg.append("</fb:editor>");
        respMsg.append("</DIV>");
        return respMsg.toString();

    }

    // -------------------------------------------------------------Main Logic
    // Processing. Process the Http request.

    public void processRequest(Map<String, String> requestParams) {

        int userId = 0;
        String userAction = null;
        List<Shopping> commodityList = null;
        String buyInfo = null;
        String cIdBuy = null;
        String cPriceBuy = null;
        String cQuantityBuy = null;
        String sellInfo = null;
        String cIdSell = null;
        String cPriceSell = null;
        String cQuantitySell = null;
        String cQuantityCartTotal = null;

        int locationId = 0;

        String amount = null;

        userId = this.gsbr.getUserId();

        // this.gsbr.setFacebookXmlRestClient();

        // -------------------------------------------------------------------------------User
        // Actions

        if (this.gsbr.getIsNewUser() == 1) {
            // reset all the use information
            initiateGameState(userId);

            // Initial location is at SFO
            locationId = 13;
            // Set user information
            this.resetUserGS(userId);
            // Set cart information
            this.resetCartGS(userId);
            // Set market information
            this.resetMarketGS(userId, locationId);

            this.getGS().setLocationId(locationId);

        } else {

            userAction = requestParams.get("userAction");

            if (userAction == null) {

                if (this.getGS().getIsInit() == 0) {

                    initiateGameState(userId);

                    // Initial location is at SFO
                    locationId = 13;
                    // Set user information
                    this.resetUserGS(userId);
                    // Set cart information
                    this.resetCartGS(userId);
                    // Set market information
                    this.resetMarketGS(userId, locationId);
                    // Set locationId
                    this.gsbr.setLocationId(locationId);

                } else {
                    // initiateGameState(userId);
                    // User is redirected from internal page
                    // Get the current locationId.
                    locationId = this.getGS().getLocationId();

                    if (locationId == 0) {
                        locationId = 13;
                        this.getGS().setLocationId(locationId);
                    }
                }

            } else {

                if (userAction.equalsIgnoreCase("restart")) {

                    System.out.println(userId + " restarted the game!");

                    // Reset all the values to initial values
                    initiateGameState(userId);
                    // Initial location is at SFO
                    locationId = 13;

                    this.getGS().setLocationId(locationId);
                    // Set user information
                    this.resetUserGS(userId);
                    // Set cart information
                    this.resetCartGS(userId);
                    // Set market information
                    this.resetMarketGS(userId, locationId);
                    // Delete all the auctions related with the userId
                    this.cleanAuction(userId);

                } else if (userAction.equalsIgnoreCase("save")) {

                    // Save Shopping, Cart, User, location information to
                    // database

                } else if (userAction.equalsIgnoreCase("invite")) {

                    locationId = this.getGS().getLocationId();
                    this.gsbr.setIsNewUser(1);

                } else {
                    String locationStr = requestParams.get("location");
                    if (locationStr == null) {
                        locationId = this.getGS().getLocationId();
                    } else {
                        locationId = new Integer(requestParams.get("location"));
                    }

                    this.getGS().setLocationId(locationId);

                    if (userAction.equalsIgnoreCase("buy")) {
                        // User buys from Market

                        System.out.println("User is buying");
                        buyInfo = requestParams.get("buyInfo");

                        // Separate buy information
                        if (buyInfo != null) {
                            // System.out.println(buyInfo);
                            try {
                                int first = buyInfo.indexOf("_");
                                cIdBuy = buyInfo.substring(0, first);
                                buyInfo = buyInfo.substring(
                                        first + 1,
                                        buyInfo.length());
                                cPriceBuy = buyInfo;
                                cQuantityBuy = requestParams
                                        .get("cQuantityBuy");

                                // Update user information
                                int total = (new Integer(cPriceBuy))
                                        * (new Integer(cQuantityBuy));
                                int moneyUsed = this.updateUserMoneyGS(
                                        new Integer(userId),
                                        total,
                                        1);
                                int itemBought = moneyUsed
                                        / (new Integer(cPriceBuy));
                                // Update cart information
                                if (new Integer(cQuantityBuy) != 0) {
                                    this.updateCartGS(
                                            new Integer(userId),
                                            new Integer(cIdBuy),
                                            itemBought,
                                            new Integer(cPriceBuy));
                                }
                                // Update user total capacity
                                this.updateUserCapacityGS(
                                        new Integer(userId),
                                        itemBought,
                                        1);
                            } catch (Exception e) {
                                System.out.println("buyInfo incorrect!");
                            }
                        }

                    } else if (userAction.equalsIgnoreCase("sell")) {

                        // User sells from cart
                        System.out.println("User is selling");
                        sellInfo = requestParams.get("sellInfo");

                        // Separate the sell information
                        if (sellInfo != null) {
                            // System.out.println(sellInfo);
                            try {
                                int first = sellInfo.indexOf("_");
                                cIdSell = sellInfo.substring(0, first);
                                sellInfo = sellInfo.substring(
                                        first + 1,
                                        sellInfo.length());
                                int second = sellInfo.indexOf("_");
                                cPriceSell = sellInfo.substring(0, second);
                                sellInfo = sellInfo.substring(
                                        second + 1,
                                        sellInfo.length());
                                int third = sellInfo.indexOf("_");
                                cQuantityCartTotal = sellInfo.substring(
                                        0,
                                        third);
                                sellInfo = sellInfo.substring(
                                        third + 1,
                                        sellInfo.length());
                                String sellOriginalPrice = sellInfo;

                                cQuantitySell = requestParams
                                        .get("cQuantitySell");

                                // Check if the sold item is in the market
                                // Only when the item exists in the market can
                                // it be sold
                                int isSoldable = 0;
                                commodityList = this.getMarketListByuIdlIdGS(
                                        userId,
                                        locationId);
                                for (int t = 0; t < commodityList.size(); t++) {
                                    if (cIdSell.equalsIgnoreCase(Integer
                                            .toString((commodityList.get(t)
                                                    .getId().getCid())))) {
                                        isSoldable = 1;
                                        break;
                                    }
                                }

                                // The item is sodable
                                if (isSoldable == 1) {
                                    if (new Integer(cQuantitySell) > new Integer(
                                            cQuantityCartTotal)) {
                                        // System.out.println("Selling too
                                        // much");
                                        cQuantitySell = cQuantityCartTotal;
                                    }

                                    // Update cart information
                                    this.updateCartSellGS(
                                            new Integer(userId),
                                            new Integer(cIdSell),
                                            new Integer(cQuantitySell),
                                            new Integer(sellOriginalPrice));

                                    // Update user information
                                    int total = (new Integer(cPriceSell))
                                            * (new Integer(cQuantitySell));
                                    this.updateUserMoneyGS(
                                            new Integer(userId),
                                            total,
                                            0);
                                    this.updateUserCapacityGS(
                                            new Integer(userId),
                                            new Integer(cQuantitySell),
                                            0);
                                }
                            } catch (Exception e) {
                                System.out.println("sellinfo incorrect!");
                            }
                        }
                    } else if (userAction.equalsIgnoreCase("move")) {

                        // User is moving around
                        System.out
                                .println("User is moving around!!!!!!!!!!!!!!!!!!");

                        // Update market information
                        this.resetMarketGS(new Integer(userId), locationId);
                        // Update user information
                        this.updateUserTTLGS(new Integer(userId), 1);

                        // Check if TTL=41
                        if (this.getCurrentUserGS().getUttl().intValue() == 0) {
                            // Game is over!!!
                            this.gsbr.setIsGameOver(1);
                            // Save user current record into record table!
                            User u = this.getCurrentUserGS();
                            Date currentTime = new Date();
                            currentTime.setTime(System.currentTimeMillis());
                            this.addRecordGS(
                                    userId,
                                    u.getUname(),
                                    u.getUmoney(),
                                    u.getUdeposit(),
                                    u.getUhealth(),
                                    u.getUreputation(),
                                    u.getUdebt(),
                                    currentTime);
                            this.addRecord(
                                    userId,
                                    u.getUname(),
                                    u.getUmoney(),
                                    u.getUdeposit(),
                                    u.getUhealth(),
                                    u.getUreputation(),
                                    u.getUdebt(),
                                    currentTime);
                        }

                        // Generate random event
                        Random randFunc = new Random();
                        int rFunc = randFunc.nextInt(2) + 1;
                        Random randCode = new Random();
                        int rCode = randCode.nextInt(5) + 1;
                        Event randEv = this.getEvent(rFunc, rCode);

                        Random r = new Random();
                        int rMount = r.nextInt(randEv.getEmount());

                        if (rFunc == 1) {
                            // win money
                            this.updateUserMoneyGS(
                                    new Integer(userId),
                                    rMount,
                                    0);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Cash + " + rMount,
                                    1);
                        } else if (rFunc == 2) {
                            // lose money
                            this.updateUserMoneyGS(
                                    new Integer(userId),
                                    rMount,
                                    1);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Cash - " + rMount,
                                    2);
                        } else if (rFunc == 3) {
                            // lose money
                            this.updateUserHealthGS(
                                    new Integer(userId),
                                    rMount,
                                    0);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Health + " + rMount,
                                    1);
                        } else if (rFunc == 4) {
                            // lose money
                            this.updateUserHealthGS(
                                    new Integer(userId),
                                    rMount,
                                    1);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Health - " + rMount,
                                    2);
                        } else if (rFunc == 5) {
                            // lose money
                            this.updateUserReputationGS(
                                    new Integer(userId),
                                    rMount,
                                    0);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Reputation + "
                                            + rMount,
                                    1);
                        } else if (rFunc == 6) {
                            // lose money
                            this.updateUserReputationGS(
                                    new Integer(userId),
                                    rMount,
                                    1);
                            this.updateFirstNewsGS(
                                    userId,
                                    0,
                                    1,
                                    randEv.getEdesp() + " Reputation - "
                                            + rMount,
                                    2);
                        }

                        rFunc = 7;
                        randEv = this.getEvent(rFunc, rCode);
                        this.updateNewsGS(
                                userId,
                                1,
                                2,
                                randEv.getEdesp(),
                                2,
                                r.nextInt(5));

                        // Get the current user's ranking and the top rank's
                        // name and cash
                        int rank = 0;
                        int recordCash = 0;

                        int topUserId = 0;
                        int topUserCash = 0;
                        String topUserName;

                        List<Record> recordList = this.getSortedRecordGS();
                        Record record = null;

                        if (recordList.size() != 0) {
                            record = (Record) recordList
                                    .get(recordList.size() - 1);
                            topUserId = record.getUid().intValue();
                            topUserCash = record.getUcash().intValue();
                            topUserName = this.getUserNameByUserIdGS(record
                                    .getUid());

                            for (int t = recordList.size() - 1; t >= 0; t--) {
                                record = (Record) recordList.get(t);
                                if (record.getUid() == userId) {
                                    rank = recordList.size() - t;
                                    recordCash = record.getUcash();
                                    break;
                                }
                            }

                            rFunc = 8;
                            randEv = this.getEvent(rFunc, 1);

                            // User type field to exchange userId
                            this.updateNewsGS(
                                    userId,
                                    2,
                                    userId,
                                    randEv.getEdesp() + " " + rank
                                            + " total: $" + recordCash,
                                    4,
                                    0);

                            rFunc = 8;
                            randEv = this.getEvent(rFunc, 2);
                            this.updateNewsGS(
                                    userId,
                                    3,
                                    topUserId,
                                    randEv.getEdesp() + " " + topUserName
                                            + " total: $" + topUserCash,
                                    3,
                                    0);
                        }

                        // Update Auction
                        // updateAuctionTTL(userId);
                        // checkoutAuction(userId);
                        // checkoutBid(userId);

                    } else if (userAction.equalsIgnoreCase("pawn")) {

                        // User is selling in the pawn shop
                        System.out.println("User is pawning");
                        sellInfo = requestParams.get("sellInfo");

                        // Separate the sell information
                        if (sellInfo != null) {
                            // System.out.println(sellInfo);
                            int first = sellInfo.indexOf("_");
                            cIdSell = sellInfo.substring(0, first);
                            sellInfo = sellInfo.substring(
                                    first + 1,
                                    sellInfo.length());
                            int second = sellInfo.indexOf("_");
                            cPriceSell = sellInfo.substring(0, second);
                            sellInfo = sellInfo.substring(
                                    second + 1,
                                    sellInfo.length());
                            cQuantityCartTotal = sellInfo;
                            cQuantitySell = requestParams.get("cQuantitySell");

                            // If the input sell quantity > available quantity,
                            // set the
                            // sell quantity = available quantity.
                            if (new Integer(cQuantitySell) > new Integer(
                                    cQuantityCartTotal)) {
                                System.out.println("Selling too much");
                                cQuantitySell = cQuantityCartTotal;
                            }

                            // Update cart information
                            this.updateCartSellGS(
                                    new Integer(userId),
                                    new Integer(cIdSell),
                                    new Integer(cQuantitySell),
                                    new Integer(cPriceSell));

                            // Update user information
                            int total = (new Integer(cPriceSell))
                                    * (new Integer(cQuantitySell)) * 3 / 5;
                            this.updateUserMoneyGS(
                                    new Integer(userId),
                                    total,
                                    0);
                            this.updateUserCapacityGS(
                                    new Integer(userId),
                                    new Integer(cQuantitySell),
                                    0);

                        }

                    } else if (userAction.equalsIgnoreCase("deposit")) {

                        // User deposit

                        System.out.println("User is depositing");

                        amount = requestParams.get("amount");

                        // Update user information
                        this.updateUserMoneyGS(
                                new Integer(userId),
                                new Integer(amount),
                                1);
                        this.updateUserDepositGS(
                                new Integer(userId),
                                new Integer(amount),
                                1);

                    } else if (userAction.equalsIgnoreCase("withdraw")) {

                        // User is withdrawing
                        System.out.println("User is withdrawing");

                        amount = requestParams.get("amount");

                        // Update user information
                        this.updateUserMoneyGS(
                                new Integer(userId),
                                new Integer(amount),
                                1);
                        this.updateUserDepositGS(
                                new Integer(userId),
                                new Integer(amount),
                                0);
                    } else if (userAction.equalsIgnoreCase("bid")) {

                        System.out.println("User is bidding");
                        String bidInfo = requestParams.get("bidInfo");

                        // Separate buy information
                        if (bidInfo != null) {
                            // System.out.println(bidInfo);
                            int first = bidInfo.indexOf("_");
                            String cIdBid = bidInfo.substring(0, first);
                            bidInfo = bidInfo.substring(
                                    first + 1,
                                    bidInfo.length());
                            int second = bidInfo.indexOf("_");
                            String uidBid = bidInfo.substring(0, second);

                            String cPriceBid = requestParams.get("cUnitPrice");

                            makeYourBid(
                                    new Integer(cIdBid),
                                    new Integer(uidBid),
                                    userId,
                                    new Integer(cPriceBid),
                                    0);
                        }

                    } else if (userAction.equalsIgnoreCase("auction")) {

                        System.out.println("User is auctioning");
                        String auctionInfo = requestParams.get("auctionInfo");

                        // Separate buy information
                        if (auctionInfo != null) {
                            // System.out.println(auctionInfo);
                            int first = auctionInfo.indexOf("_");
                            cIdSell = auctionInfo.substring(0, first);
                            auctionInfo = auctionInfo.substring(
                                    first + 1,
                                    auctionInfo.length());
                            int second = auctionInfo.indexOf("_");
                            cPriceSell = auctionInfo.substring(0, second);
                            auctionInfo = auctionInfo.substring(
                                    second + 1,
                                    auctionInfo.length());
                            cQuantityCartTotal = auctionInfo;

                            cQuantitySell = requestParams.get("cQuantitySell");

                            String cUnitPrice = requestParams.get("cUnitPrice");
                            String cQuantity = requestParams.get("cQuantity");
                            String cTTL = requestParams.get("cTTL");

                            makeYourAuction(
                                    new Integer(cIdSell),
                                    new Integer(userId),
                                    new Integer(cPriceSell),
                                    new Integer(cUnitPrice),
                                    new Integer(cQuantity),
                                    new Integer(cTTL));
                        }

                    } else if (userAction.equalsIgnoreCase("sendemail")) {
                        this.gsbr.setSendEmail(1);

                    }
                }
            }
        }
    }

    public void initiateGameState(int userId) {

        System.out.println("******************Initialize Game State");
        // Import all the data from database
        this.gsbr.setUser(this.getUserById(userId));
        this.gsbr.setAllUserList(this.getAllUser());
        this.gsbr.setCommodityList(this.getCommodity());
        this.gsbr.setCartList(this.getCartList(userId));
        this.gsbr.setEventList(this.getEvent());
        this.gsbr.setLocationList(this.getLocation());
        this.gsbr.setNewsList(this.getNewsListByUid(userId));
        this.gsbr.setShoppingList(this.getMarketList(userId));
        // Advertise type --1-- is the advertise for commodity list
        this.gsbr.setAdvertisingList(this.getAllAdvertising());

        this.gsbr.setRecordList(this.getAllRecord());

        this.gsbr.setIsGameOver(0);
        // Gaming State initiated
        this.gsbr.setIsInit();

    }
}
