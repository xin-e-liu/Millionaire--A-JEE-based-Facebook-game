/*
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
*/

package com.lostus.ejb.auction;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractAuction entity provides the base persistence definition of the
 * Auction entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractAuction implements java.io.Serializable {

	// Fields

	private AuctionId id;
	private Integer lid;
	private Integer unitPrice;
	private Integer quantity;
	private Integer bidPrice;
	private Integer auctionTime;
	private Integer timeLeft;
	private Integer bidTime;
	private Integer isMaxBid;
	private Integer originalPrice;

	// Constructors

	/** default constructor */
	public AbstractAuction() {
	}

	/** minimal constructor */
	public AbstractAuction(AuctionId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractAuction(AuctionId id, Integer lid, Integer unitPrice,
			Integer quantity, Integer bidPrice, Integer auctionTime,
			Integer timeLeft, Integer bidTime, Integer isMaxBid,
			Integer originalPrice) {
		this.id = id;
		this.lid = lid;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.bidPrice = bidPrice;
		this.auctionTime = auctionTime;
		this.timeLeft = timeLeft;
		this.bidTime = bidTime;
		this.isMaxBid = isMaxBid;
		this.originalPrice = originalPrice;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "cid", column = @Column(name = "CID", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "uid", column = @Column(name = "UID", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "bidUid", column = @Column(name = "BidUID", unique = false, nullable = false, insertable = true, updatable = true)) })
	public AuctionId getId() {
		return this.id;
	}

	public void setId(AuctionId id) {
		this.id = id;
	}

	@Column(name = "LID", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	@Column(name = "UnitPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "Quantity", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "BidPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getBidPrice() {
		return this.bidPrice;
	}

	public void setBidPrice(Integer bidPrice) {
		this.bidPrice = bidPrice;
	}

	@Column(name = "AuctionTime", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getAuctionTime() {
		return this.auctionTime;
	}

	public void setAuctionTime(Integer auctionTime) {
		this.auctionTime = auctionTime;
	}

	@Column(name = "TimeLeft", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(Integer timeLeft) {
		this.timeLeft = timeLeft;
	}

	@Column(name = "BidTime", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getBidTime() {
		return this.bidTime;
	}

	public void setBidTime(Integer bidTime) {
		this.bidTime = bidTime;
	}

	@Column(name = "isMaxBid", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getIsMaxBid() {
		return this.isMaxBid;
	}

	public void setIsMaxBid(Integer isMaxBid) {
		this.isMaxBid = isMaxBid;
	}

	@Column(name = "OriginalPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getOriginalPrice() {
		return this.originalPrice;
	}

	public void setOriginalPrice(Integer originalPrice) {
		this.originalPrice = originalPrice;
	}

}