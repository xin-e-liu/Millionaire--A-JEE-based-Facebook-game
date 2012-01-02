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

package com.lostus.ejb.commodity;

import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Commodity.
 * 
 * @see com.lostus.ejb.commodity.Commodity
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class CommodityFacade implements CommodityFacadeLocal,
		CommodityFacadeRemote {
	// property constants
	public static final String CNAME = "cname";
	public static final String CPROPERTY = "cproperty";
	public static final String CMIN_PRICE = "cminPrice";
	public static final String CMAX_PRICE = "cmaxPrice";
	public static final String CMAX_QUANTITY = "cmaxQuantity";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Commodity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Commodity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Commodity entity) {
		LogUtil.log("saving Commodity instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Commodity entity.
	 * 
	 * @param entity
	 *            Commodity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Commodity entity) {
		LogUtil.log("deleting Commodity instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Commodity.class, entity
					.getCid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Commodity entity and return it or a copy of it
	 * to the sender. A copy of the Commodity entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Commodity entity to update
	 * @returns Commodity the persisted Commodity entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Commodity update(Commodity entity) {
		LogUtil.log("updating Commodity instance", Level.INFO, null);
		try {
			Commodity result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Commodity findById(Integer id) {
		LogUtil.log("finding Commodity instance with id: " + id, Level.INFO,
				null);
		try {
			Commodity instance = entityManager.find(Commodity.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Commodity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Commodity property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Commodity> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Commodity> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Commodity instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Commodity model where model."
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

	public List<Commodity> findByCname(Object cname, int... rowStartIdxAndCount) {
		return findByProperty(CNAME, cname, rowStartIdxAndCount);
	}

	public List<Commodity> findByCproperty(Object cproperty,
			int... rowStartIdxAndCount) {
		return findByProperty(CPROPERTY, cproperty, rowStartIdxAndCount);
	}

	public List<Commodity> findByCminPrice(Object cminPrice,
			int... rowStartIdxAndCount) {
		return findByProperty(CMIN_PRICE, cminPrice, rowStartIdxAndCount);
	}

	public List<Commodity> findByCmaxPrice(Object cmaxPrice,
			int... rowStartIdxAndCount) {
		return findByProperty(CMAX_PRICE, cmaxPrice, rowStartIdxAndCount);
	}

	public List<Commodity> findByCmaxQuantity(Object cmaxQuantity,
			int... rowStartIdxAndCount) {
		return findByProperty(CMAX_QUANTITY, cmaxQuantity, rowStartIdxAndCount);
	}

	/**
	 * Find all Commodity entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Commodity> all Commodity entities
	 */
	@SuppressWarnings("unchecked")
	public List<Commodity> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Commodity instances", Level.INFO, null);
		try {
			final String queryString = "select model from Commodity model";
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