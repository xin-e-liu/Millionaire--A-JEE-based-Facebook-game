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

package com.lostus.ejb.news;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity News.
 * 
 * @see com.lostus.ejb.news.News
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class NewsFacade implements NewsFacadeLocal, NewsFacadeRemote {
	// property constants
	public static final String CONTENT = "content";
	public static final String TYPE = "type";
	public static final String PRIORITY = "priority";
	public static final String UID = "uid";
	public static final String ID_IN_TYPE = "idInType";
	public static final String PROPERTY = "property";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved News entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            News entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(News entity) {
		LogUtil.log("saving News instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent News entity.
	 * 
	 * @param entity
	 *            News entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(News entity) {
		LogUtil.log("deleting News instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(News.class, entity.getId());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved News entity and return it or a copy of it to
	 * the sender. A copy of the News entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            News entity to update
	 * @returns News the persisted News entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public News update(News entity) {
		LogUtil.log("updating News instance", Level.INFO, null);
		try {
			News result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public News findById(Integer id) {
		LogUtil.log("finding News instance with id: " + id, Level.INFO, null);
		try {
			News instance = entityManager.find(News.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all News entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the News property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<News> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<News> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding News instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from News model where model."
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

	public List<News> findByContent(Object content, int... rowStartIdxAndCount) {
		return findByProperty(CONTENT, content, rowStartIdxAndCount);
	}

	public List<News> findByType(Object type, int... rowStartIdxAndCount) {
		return findByProperty(TYPE, type, rowStartIdxAndCount);
	}

	public List<News> findByPriority(Object priority,
			int... rowStartIdxAndCount) {
		return findByProperty(PRIORITY, priority, rowStartIdxAndCount);
	}

	public List<News> findByUid(Object uid, int... rowStartIdxAndCount) {
		return findByProperty(UID, uid, rowStartIdxAndCount);
	}

	public List<News> findByIdInType(Object idInType,
			int... rowStartIdxAndCount) {
		return findByProperty(ID_IN_TYPE, idInType, rowStartIdxAndCount);
	}

	public List<News> findByProperty(Object property,
			int... rowStartIdxAndCount) {
		return findByProperty(PROPERTY, property, rowStartIdxAndCount);
	}

	/**
	 * Find all News entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<News> all News entities
	 */
	@SuppressWarnings("unchecked")
	public List<News> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all News instances", Level.INFO, null);
		try {
			final String queryString = "select model from News model";
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