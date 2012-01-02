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

package com.lostus.ejb.location;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Location.
 * 
 * @see com.lostus.ejb.location.Location
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class LocationFacade implements LocationFacadeLocal,
		LocationFacadeRemote {
	// property constants
	public static final String LNAME = "lname";
	public static final String LTYPE = "ltype";
	public static final String LPROPERTY = "lproperty";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Location entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Location entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Location entity) {
		LogUtil.log("saving Location instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Location entity.
	 * 
	 * @param entity
	 *            Location entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Location entity) {
		LogUtil.log("deleting Location instance", Level.INFO, null);
		try {
			entity = entityManager
					.getReference(Location.class, entity.getLid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Location entity and return it or a copy of it
	 * to the sender. A copy of the Location entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Location entity to update
	 * @returns Location the persisted Location entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Location update(Location entity) {
		LogUtil.log("updating Location instance", Level.INFO, null);
		try {
			Location result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Location findById(Integer id) {
		LogUtil.log("finding Location instance with id: " + id, Level.INFO,
				null);
		try {
			Location instance = entityManager.find(Location.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Location entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Location property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Location> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Location> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		LogUtil.log("finding Location instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Location model where model."
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

	public List<Location> findByLname(Object lname, int... rowStartIdxAndCount) {
		return findByProperty(LNAME, lname, rowStartIdxAndCount);
	}

	public List<Location> findByLtype(Object ltype, int... rowStartIdxAndCount) {
		return findByProperty(LTYPE, ltype, rowStartIdxAndCount);
	}

	public List<Location> findByLproperty(Object lproperty,
			int... rowStartIdxAndCount) {
		return findByProperty(LPROPERTY, lproperty, rowStartIdxAndCount);
	}

	/**
	 * Find all Location entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Location> all Location entities
	 */
	@SuppressWarnings("unchecked")
	public List<Location> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Location instances", Level.INFO, null);
		try {
			final String queryString = "select model from Location model";
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