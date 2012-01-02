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

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Auction entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Auction", catalog = "LostUS", uniqueConstraints = {})
public class Auction extends AbstractAuction implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Auction() {
	}

	/** minimal constructor */
	public Auction(AuctionId id) {
		super(id);
	}

	/** full constructor */
	public Auction(AuctionId id, Integer lid, Integer unitPrice,
			Integer quantity, Integer bidPrice, Integer auctionTime,
			Integer timeLeft, Integer bidTime, Integer isMaxBid,
			Integer originalPrice) {
		super(id, lid, unitPrice, quantity, bidPrice, auctionTime, timeLeft,
				bidTime, isMaxBid, originalPrice);
	}

}
