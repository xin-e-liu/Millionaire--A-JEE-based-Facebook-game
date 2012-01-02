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

package com.lostus.ejb.advertising;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Advertising.
 * 
 * @see com.lostus.ejb.advertising.Advertising
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class AdvertisingFacade implements AdvertisingFacadeLocal,
		AdvertisingFacadeRemote {
	// property constants
	public static final String ADLINK = "adlink";
	public static final String ADIMAGE = "adimage";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Advertising entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Advertising entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Advertising entity) {
		LogUtil.log("saving Advertising instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Advertising entity.
	 * 
	 * @param entity
	 *            Advertising entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Advertising entity) {
		LogUtil.log("deleting Advertising instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Advertising.class, entity
					.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Advertising entity and return it or a copy of
	 * it to the sender. A copy of the Advertising entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Advertising entity to update
	 * @returns Advertising the persisted Advertising entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Advertising update(Advertising entity) {
		LogUtil.log("updating Advertising instance", Level.INFO, null);
		try {
			Advertising result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Advertising findById(AdvertisingId id) {
		LogUtil.log("finding Advertising instance with id: " + id, Level.INFO,
				null);
		try {
			Advertising instance = entityManager.find(Advertising.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Advertising entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Advertising property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Advertising> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Advertising> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Advertising instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Advertising model where model."
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

	public List<Advertising> findByAdlink(Object adlink,
			int... rowStartIdxAndCount) {
		return findByProperty(ADLINK, adlink, rowStartIdxAndCount);
	}

	public List<Advertising> findByAdimage(Object adimage,
			int... rowStartIdxAndCount) {
		return findByProperty(ADIMAGE, adimage, rowStartIdxAndCount);
	}
	
	public List<Advertising> findByAdType(Object adtype,
			int... rowStartIdxAndCount) {
		return findByProperty("id.adtype", adtype, rowStartIdxAndCount);
	}

	/**
	 * Find all Advertising entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Advertising> all Advertising entities
	 */
	@SuppressWarnings("unchecked")
	public List<Advertising> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Advertising instances", Level.INFO, null);
		try {
			final String queryString = "select model from Advertising model";
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