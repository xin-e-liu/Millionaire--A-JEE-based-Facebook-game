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

package com.lostus.ejb.record;

import com.lostus.ejb.commodity.LogUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity Record.
 * 
 * @see com.lostus.ejb.record.Record
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class RecordFacade implements RecordFacadeLocal, RecordFacadeRemote {
	// property constants
	public static final String UID = "uid";
	public static final String UCASH = "ucash";
	public static final String UDEPOSIT = "udeposit";
	public static final String UHEALTH = "uhealth";
	public static final String UREPUTATION = "ureputation";
	public static final String UDEBT = "udebt";
	public static final String UNAME = "uname";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved Record entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Record entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Record entity) {
		LogUtil.log("saving Record instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent Record entity.
	 * 
	 * @param entity
	 *            Record entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Record entity) {
		LogUtil.log("deleting Record instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(Record.class, entity.getRid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved Record entity and return it or a copy of it to
	 * the sender. A copy of the Record entity parameter is returned when the
	 * JPA persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            Record entity to update
	 * @returns Record the persisted Record entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Record update(Record entity) {
		LogUtil.log("updating Record instance", Level.INFO, null);
		try {
			Record result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public Record findById(Integer id) {
		LogUtil.log("finding Record instance with id: " + id, Level.INFO, null);
		try {
			Record instance = entityManager.find(Record.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all Record entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Record property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<Record> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<Record> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding Record instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from Record model where model."
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

	public List<Record> findByUid(Object uid, int... rowStartIdxAndCount) {
		return findByProperty(UID, uid, rowStartIdxAndCount);
	}

	public List<Record> findByUcash(Object ucash, int... rowStartIdxAndCount) {
		return findByProperty(UCASH, ucash, rowStartIdxAndCount);
	}

	public List<Record> findByUdeposit(Object udeposit,
			int... rowStartIdxAndCount) {
		return findByProperty(UDEPOSIT, udeposit, rowStartIdxAndCount);
	}

	public List<Record> findByUhealth(Object uhealth,
			int... rowStartIdxAndCount) {
		return findByProperty(UHEALTH, uhealth, rowStartIdxAndCount);
	}

	public List<Record> findByUreputation(Object ureputation,
			int... rowStartIdxAndCount) {
		return findByProperty(UREPUTATION, ureputation, rowStartIdxAndCount);
	}

	public List<Record> findByUdebt(Object udebt, int... rowStartIdxAndCount) {
		return findByProperty(UDEBT, udebt, rowStartIdxAndCount);
	}

	public List<Record> findByUname(Object uname, int... rowStartIdxAndCount) {
		return findByProperty(UNAME, uname, rowStartIdxAndCount);
	}

	/**
	 * Find all Record entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Record> all Record entities
	 */
	@SuppressWarnings("unchecked")
	public List<Record> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all Record instances", Level.INFO, null);
		try {
			final String queryString = "select model from Record model";
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