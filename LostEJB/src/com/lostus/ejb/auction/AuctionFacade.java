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

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Auction.
 * 
 * @see com.lostus.ejb.auction.Auction
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AuctionFacade implements AuctionFacadeLocal, AuctionFacadeRemote {
	// property constants
	public static final String LID = "lid";
	public static final String UNIT_PRICE = "unitPrice";
	public static final String QUANTITY = "quantity";
	public static final String BID_PRICE = "bidPrice";
	public static final String AUCTION_TIME = "auctionTime";
	public static final String TIME_LEFT = "timeLeft";
	public static final String BID_TIME = "bidTime";
	public static final String IS_MAX_BID = "isMaxBid";
	public static final String ORIGINAL_PRICE = "originalPrice";

	@PersistenceContext
	private EntityManager entityManager;

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
	public void save(Auction entity) {
		LogUtil.log("saving Auction instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Auction entity.
	 * 
	 * @param entity
	 *            Auction entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Auction entity) {
		LogUtil.log("deleting Auction instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Auction.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	public Auction update(Auction entity) {
		LogUtil.log("updating Auction instance", Level.INFO, null);
		try {
			Auction result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Auction findById(AuctionId id) {
		LogUtil
				.log("finding Auction instance with id: " + id, Level.INFO,
						null);
		try {
			Auction instance = entityManager.find(Auction.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

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
	 *            number of results to return.
	 * @return List<Auction> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Auction> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Auction instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Auction model where model."
					+ propertyName + "= :propertyValue";
			Query query = entityManager.createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find by property name failed", Level.SEVERE, re);
			throw re;
		}
	}

	public List<Auction> findByLid(Object lid, int... rowStartIdxAndCount) {
		return findByProperty(LID, lid, rowStartIdxAndCount);
	}

	public List<Auction> findByUnitPrice(Object unitPrice,
			int... rowStartIdxAndCount) {
		return findByProperty(UNIT_PRICE, unitPrice, rowStartIdxAndCount);
	}

	public List<Auction> findByQuantity(Object quantity,
			int... rowStartIdxAndCount) {
		return findByProperty(QUANTITY, quantity, rowStartIdxAndCount);
	}

	public List<Auction> findByBidPrice(Object bidPrice,
			int... rowStartIdxAndCount) {
		return findByProperty(BID_PRICE, bidPrice, rowStartIdxAndCount);
	}

	public List<Auction> findByAuctionTime(Object auctionTime,
			int... rowStartIdxAndCount) {
		return findByProperty(AUCTION_TIME, auctionTime, rowStartIdxAndCount);
	}

	public List<Auction> findByTimeLeft(Object timeLeft,
			int... rowStartIdxAndCount) {
		return findByProperty(TIME_LEFT, timeLeft, rowStartIdxAndCount);
	}

	public List<Auction> findByBidTime(Object bidTime,
			int... rowStartIdxAndCount) {
		return findByProperty(BID_TIME, bidTime, rowStartIdxAndCount);
	}

	public List<Auction> findByIsMaxBid(Object isMaxBid,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_MAX_BID, isMaxBid, rowStartIdxAndCount);
	}

	public List<Auction> findByOriginalPrice(Object originalPrice,
			int... rowStartIdxAndCount) {
		return findByProperty(ORIGINAL_PRICE, originalPrice,
				rowStartIdxAndCount);
	}
	
	public List<Auction> findByBidUid(Object bidUid,
			int... rowStartIdxAndCount) {
		return findByProperty("id.bidUid", bidUid, rowStartIdxAndCount);
	}
	
	public List<Auction> findByUid(Object uid,
			int... rowStartIdxAndCount) {
		return findByProperty("id.uid", uid, rowStartIdxAndCount);
	}

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
	@SuppressWarnings("unchecked")
	public List<Auction> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Auction instances", Level.INFO, null);
		try {
			final String queryString = "select model from Auction model";
			Query query = entityManager.createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			LogUtil.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}