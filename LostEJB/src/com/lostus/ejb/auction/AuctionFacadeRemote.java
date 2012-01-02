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

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for AuctionFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface AuctionFacadeRemote {
	/**
	 * Perform an initial save of a previously unsaved Auction entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Auction entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Auction entity);

	/**
	 * Delete a persistent Auction entity.
	 * 
	 * @param entity
	 *            Auction entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Auction entity);

	/**
	 * Persist a previously saved Auction entity and return it or a copy of it
	 * to the sender. A copy of the Auction entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Auction entity to update
	 * @returns Auction the persisted Auction entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Auction update(Auction entity);

	public Auction findById(AuctionId id);

	/**
	 * Find all Auction entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Auction property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Auction> found by query
	 */
	public List<Auction> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Auction> findByLid(Object lid, int... rowStartIdxAndCount);

	public List<Auction> findByUnitPrice(Object unitPrice,
			int... rowStartIdxAndCount);

	public List<Auction> findByQuantity(Object quantity,
			int... rowStartIdxAndCount);

	public List<Auction> findByBidPrice(Object bidPrice,
			int... rowStartIdxAndCount);

	public List<Auction> findByAuctionTime(Object auctionTime,
			int... rowStartIdxAndCount);

	public List<Auction> findByTimeLeft(Object timeLeft,
			int... rowStartIdxAndCount);

	public List<Auction> findByBidTime(Object bidTime,
			int... rowStartIdxAndCount);

	public List<Auction> findByIsMaxBid(Object isMaxBid,
			int... rowStartIdxAndCount);

	public List<Auction> findByOriginalPrice(Object originalPrice,
			int... rowStartIdxAndCount);
	
	public List<Auction> findByBidUid(Object bidUid,
			int... rowStartIdxAndCount);
	
	public List<Auction> findByUid(Object uid,
			int... rowStartIdxAndCount);

	/**
	 * Find all Auction entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Auction> all Auction entities
	 */
	public List<Auction> findAll(int... rowStartIdxAndCount);
}