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

package com.lostus.ejb.event;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Event.
 * 
 * @see com.lostus.ejb.event.Event
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class EventFacade implements EventFacadeLocal, EventFacadeRemote {
	// property constants
	public static final String ENAME = "ename";
	public static final String EDESP = "edesp";
	public static final String EFUNC = "efunc";
	public static final String ECODE = "ecode";
	public static final String EMOUNT = "emount";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Event entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Event entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Event entity) {
		LogUtil.log("saving Event instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Event entity.
	 * 
	 * @param entity
	 *            Event entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Event entity) {
		LogUtil.log("deleting Event instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Event.class, entity.getEid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Event entity and return it or a copy of it to
	 * the sender. A copy of the Event entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            Event entity to update
	 * @returns Event the persisted Event entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Event update(Event entity) {
		LogUtil.log("updating Event instance", Level.INFO, null);
		try {
			Event result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Event findById(Integer id) {
		LogUtil.log("finding Event instance with id: " + id, Level.INFO, null);
		try {
			Event instance = entityManager.find(Event.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Event entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Event property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Event> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Event> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding Event instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Event model where model."
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

	public List<Event> findByEname(Object ename, int... rowStartIdxAndCount) {
		return findByProperty(ENAME, ename, rowStartIdxAndCount);
	}

	public List<Event> findByEdesp(Object edesp, int... rowStartIdxAndCount) {
		return findByProperty(EDESP, edesp, rowStartIdxAndCount);
	}

	public List<Event> findByEfunc(Object efunc, int... rowStartIdxAndCount) {
		return findByProperty(EFUNC, efunc, rowStartIdxAndCount);
	}

	public List<Event> findByEcode(Object ecode, int... rowStartIdxAndCount) {
		return findByProperty(ECODE, ecode, rowStartIdxAndCount);
	}

	public List<Event> findByEmount(Object emount, int... rowStartIdxAndCount) {
		return findByProperty(EMOUNT, emount, rowStartIdxAndCount);
	}

	/**
	 * Find all Event entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Event> all Event entities
	 */
	@SuppressWarnings("unchecked")
	public List<Event> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Event instances", Level.INFO, null);
		try {
			final String queryString = "select model from Event model";
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