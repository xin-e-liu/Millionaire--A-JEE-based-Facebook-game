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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AuctionId entity provides the base persistence definition of the d entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class AuctionId implements java.io.Serializable {

	// Fields

	private Integer cid;
	private Integer uid;
	private Integer bidUid;

	// Constructors

	/** default constructor */
	public AuctionId() {
	}

	/** full constructor */
	public AuctionId(Integer cid, Integer uid, Integer bidUid) {
		this.cid = cid;
		this.uid = uid;
		this.bidUid = bidUid;
	}

	// Property accessors

	@Column(name = "CID", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "UID", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "BidUID", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getBidUid() {
		return this.bidUid;
	}

	public void setBidUid(Integer bidUid) {
		this.bidUid = bidUid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AuctionId))
			return false;
		AuctionId castOther = (AuctionId) other;

		return ((this.getCid() == castOther.getCid()) || (this.getCid() != null
				&& castOther.getCid() != null && this.getCid().equals(
				castOther.getCid())))
				&& ((this.getUid() == castOther.getUid()) || (this.getUid() != null
						&& castOther.getUid() != null && this.getUid().equals(
						castOther.getUid())))
				&& ((this.getBidUid() == castOther.getBidUid()) || (this
						.getBidUid() != null
						&& castOther.getBidUid() != null && this.getBidUid()
						.equals(castOther.getBidUid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getBidUid() == null ? 0 : this.getBidUid().hashCode());
		return result;
	}

}