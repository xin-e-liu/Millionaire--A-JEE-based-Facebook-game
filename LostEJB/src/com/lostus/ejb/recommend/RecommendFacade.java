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

package com.lostus.ejb.recommend;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Recommend.
 * 
 * @see com.lostus.ejb.recommend.Recommend
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class RecommendFacade implements RecommendFacadeLocal,
		RecommendFacadeRemote {
	// property constants
	public static final String CSALES_AMOUNT = "csalesAmount";
	public static final String CSCORE = "cscore";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Recommend entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Recommend entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Recommend entity) {
		LogUtil.log("saving Recommend instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Recommend entity.
	 * 
	 * @param entity
	 *            Recommend entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Recommend entity) {
		LogUtil.log("deleting Recommend instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Recommend.class, entity
					.getCid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Recommend entity and return it or a copy of it
	 * to the sender. A copy of the Recommend entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Recommend entity to update
	 * @returns Recommend the persisted Recommend entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Recommend update(Recommend entity) {
		LogUtil.log("updating Recommend instance", Level.INFO, null);
		try {
			Recommend result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Recommend findById(Integer id) {
		LogUtil.log("finding Recommend instance with id: " + id, Level.INFO,
				null);
		try {
			Recommend instance = entityManager.find(Recommend.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Recommend entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Recommend property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Recommend> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Recommend> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Recommend instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Recommend model where model."
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

	public List<Recommend> findByCsalesAmount(Object csalesAmount,
			int... rowStartIdxAndCount) {
		return findByProperty(CSALES_AMOUNT, csalesAmount, rowStartIdxAndCount);
	}

	public List<Recommend> findByCscore(Object cscore,
			int... rowStartIdxAndCount) {
		return findByProperty(CSCORE, cscore, rowStartIdxAndCount);
	}

	/**
	 * Find all Recommend entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Recommend> all Recommend entities
	 */
	@SuppressWarnings("unchecked")
	public List<Recommend> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Recommend instances", Level.INFO, null);
		try {
			final String queryString = "select model from Recommend model";
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