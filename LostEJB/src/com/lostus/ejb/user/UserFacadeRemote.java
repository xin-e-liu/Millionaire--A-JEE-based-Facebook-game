package com.lostus.ejb.user;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for UserFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface UserFacadeRemote {
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
	public void save(User entity);

	/**
	 * Delete a persistent User entity.
	 * 
	 * @param entity
	 *            User entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(User entity);

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
	public User update(User entity);

	public User findById(Integer id);

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
	 *            count of results to return.
	 * @return List<User> found by query
	 */
	public List<User> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<User> findByUname(Object uname, int... rowStartIdxAndCount);

	public List<User> findByUmoney(Object umoney, int... rowStartIdxAndCount);

	public List<User> findByUttl(Object uttl, int... rowStartIdxAndCount);

	public List<User> findByUcapacity(Object ucapacity,
			int... rowStartIdxAndCount);

	public List<User> findBySessionId(Object sessionId,
			int... rowStartIdxAndCount);

	public List<User> findByUdeposit(Object udeposit,
			int... rowStartIdxAndCount);

	public List<User> findByUdebt(Object udebt, int... rowStartIdxAndCount);

	public List<User> findByUhealth(Object uhealth, int... rowStartIdxAndCount);

	public List<User> findByUreputation(Object ureputation,
			int... rowStartIdxAndCount);

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
	public List<User> findAll(int... rowStartIdxAndCount);
}