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

package com.lostus.ejb.usercart;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity UserCart.
 * 
 * @see com.lostus.ejb.usercart.UserCart
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class UserCartFacade implements UserCartFacadeLocal,
		UserCartFacadeRemote {
	// property constants
	public static final String QUANTITY = "quantity";
	public static final String LID = "lid";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved UserCart entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            UserCart entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserCart entity) {
		LogUtil.log("saving UserCart instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UserCart entity.
	 * 
	 * @param entity
	 *            UserCart entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserCart entity) {
		LogUtil.log("deleting UserCart instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(UserCart.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UserCart entity and return it or a copy of it
	 * to the sender. A copy of the UserCart entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            UserCart entity to update
	 * @returns UserCart the persisted UserCart entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserCart update(UserCart entity) {
		LogUtil.log("updating UserCart instance", Level.INFO, null);
		try {
			UserCart result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UserCart findById(UserCartId id) {
		LogUtil.log("finding UserCart instance with id: " + id, Level.INFO,
				null);
		try {
			UserCart instance = entityManager.find(UserCart.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UserCart entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserCart property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<UserCart> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UserCart> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding UserCart instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UserCart model where model."
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

	public List<UserCart> findByQuantity(Object quantity,
			int... rowStartIdxAndCount) {
		return findByProperty(QUANTITY, quantity, rowStartIdxAndCount);
	}

	public List<UserCart> findByLid(Object lid, int... rowStartIdxAndCount) {
		return findByProperty(LID, lid, rowStartIdxAndCount);
	}
	
	public List<UserCart> findByUid(Object uid,
			int... rowStartIdxAndCount) {
		return findByProperty("id.uid", uid, rowStartIdxAndCount);
	}
	
	public List<UserCart> findByCid(Object cid,
			int... rowStartIdxAndCount) {
		return findByProperty("id.cid", cid, rowStartIdxAndCount);
	}
	

	/**
	 * Find all UserCart entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCart> all UserCart entities
	 */
	@SuppressWarnings("unchecked")
	public List<UserCart> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all UserCart instances", Level.INFO, null);
		try {
			final String queryString = "select model from UserCart model";
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