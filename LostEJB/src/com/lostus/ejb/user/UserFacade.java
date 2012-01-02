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

package com.lostus.ejb.user;

import com.lostus.ejb.commodity.LogUtil;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Facade for entity User.
 * 
 * @see com.lostus.ejb.user.User
 * @author MyEclipse Persistence Tools
 */
@Stateless
public class UserFacade implements UserFacadeLocal, UserFacadeRemote {
	// property constants
	public static final String UNAME = "uname";
	public static final String UMONEY = "umoney";
	public static final String UTTL = "uttl";
	public static final String UCAPACITY = "ucapacity";
	public static final String SESSION_ID = "sessionId";
	public static final String UDEPOSIT = "udeposit";
	public static final String UDEBT = "udebt";
	public static final String UHEALTH = "uhealth";
	public static final String UREPUTATION = "ureputation";

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Perform an initial save of a previously unsaved User entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            User entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(User entity) {
		LogUtil.log("saving User instance", Level.INFO, null);
		try {
			entityManager.persist(entity);
			LogUtil.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent User entity.
	 * 
	 * @param entity
	 *            User entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(User entity) {
		LogUtil.log("deleting User instance", Level.INFO, null);
		try {
			entity = entityManager.getReference(User.class, entity.getUid());
			entityManager.remove(entity);
			LogUtil.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			LogUtil.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved User entity and return it or a copy of it to
	 * the sender. A copy of the User entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            User entity to update
	 * @returns User the persisted User entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public User update(User entity) {
		LogUtil.log("updating User instance", Level.INFO, null);
		try {
			User result = entityManager.merge(entity);
			LogUtil.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			LogUtil.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public User findById(Integer id) {
		LogUtil.log("finding User instance with id: " + id, Level.INFO, null);
		try {
			User instance = entityManager.find(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			LogUtil.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all User entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the User property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<User> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<User> findByProperty(String propertyName, final Object value,
			final int... rowStartIdxAndCount) {
		LogUtil.log("finding User instance with property: " + propertyName
				+ ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from User model where model."
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

	public List<User> findByUname(Object uname, int... rowStartIdxAndCount) {
		return findByProperty(UNAME, uname, rowStartIdxAndCount);
	}

	public List<User> findByUmoney(Object umoney, int... rowStartIdxAndCount) {
		return findByProperty(UMONEY, umoney, rowStartIdxAndCount);
	}

	public List<User> findByUttl(Object uttl, int... rowStartIdxAndCount) {
		return findByProperty(UTTL, uttl, rowStartIdxAndCount);
	}

	public List<User> findByUcapacity(Object ucapacity,
			int... rowStartIdxAndCount) {
		return findByProperty(UCAPACITY, ucapacity, rowStartIdxAndCount);
	}

	public List<User> findBySessionId(Object sessionId,
			int... rowStartIdxAndCount) {
		return findByProperty(SESSION_ID, sessionId, rowStartIdxAndCount);
	}

	public List<User> findByUdeposit(Object udeposit,
			int... rowStartIdxAndCount) {
		return findByProperty(UDEPOSIT, udeposit, rowStartIdxAndCount);
	}

	public List<User> findByUdebt(Object udebt, int... rowStartIdxAndCount) {
		return findByProperty(UDEBT, udebt, rowStartIdxAndCount);
	}

	public List<User> findByUhealth(Object uhealth, int... rowStartIdxAndCount) {
		return findByProperty(UHEALTH, uhealth, rowStartIdxAndCount);
	}

	public List<User> findByUreputation(Object ureputation,
			int... rowStartIdxAndCount) {
		return findByProperty(UREPUTATION, ureputation, rowStartIdxAndCount);
	}

	/**
	 * Find all User entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<User> all User entities
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAll(final int... rowStartIdxAndCount) {
		LogUtil.log("finding all User instances", Level.INFO, null);
		try {
			final String queryString = "select model from User model";
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