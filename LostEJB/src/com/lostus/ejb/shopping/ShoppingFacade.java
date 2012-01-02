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

package com.lostus.ejb.shopping;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Shopping.
 * 
 * @see com.lostus.ejb.shopping.Shopping
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class ShoppingFacade implements ShoppingFacadeLocal,
		ShoppingFacadeRemote {
	// property constants
	public static final String LID = "lid";
	public static final String CPRICE = "cprice";
	public static final String CQUANTITY = "cquantity";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Shopping entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Shopping entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Shopping entity) {
		LogUtil.log("saving Shopping instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Shopping entity.
	 * 
	 * @param entity
	 *            Shopping entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shopping entity) {
		LogUtil.log("deleting Shopping instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Shopping.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Shopping entity and return it or a copy of it
	 * to the sender. A copy of the Shopping entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Shopping entity to update
	 * @returns Shopping the persisted Shopping entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Shopping update(Shopping entity) {
		LogUtil.log("updating Shopping instance", Level.INFO, null);
		try {
			Shopping result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Shopping findById(ShoppingId id) {
		LogUtil.log("finding Shopping instance with id: " + id, Level.INFO,
				null);
		try {
			Shopping instance = entityManager.find(Shopping.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Shopping entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Shopping property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Shopping> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Shopping> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Shopping instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Shopping model where model."
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

	public List<Shopping> findByLid(Object lid, int... rowStartIdxAndCount) {
		return findByProperty(LID, lid, rowStartIdxAndCount);
	}

	public List<Shopping> findByCprice(Object cprice,
			int... rowStartIdxAndCount) {
		return findByProperty(CPRICE, cprice, rowStartIdxAndCount);
	}

	public List<Shopping> findByCquantity(Object cquantity,
			int... rowStartIdxAndCount) {
		return findByProperty(CQUANTITY, cquantity, rowStartIdxAndCount);
	}
	
	public List<Shopping> findByUid(Object uid,
			int... rowStartIdxAndCount) {
		return findByProperty("id.uid", uid, rowStartIdxAndCount);
	}

	/**
	 * Find all Shopping entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Shopping> all Shopping entities
	 */
	@SuppressWarnings("unchecked")
	public List<Shopping> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Shopping instances", Level.INFO, null);
		try {
			final String queryString = "select model from Shopping model";
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