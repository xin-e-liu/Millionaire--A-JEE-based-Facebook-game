package com.lostus.ejb.usercart;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for UserCartFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface UserCartFacadeRemote {
	/**
	 * Perform an initial save of a previously unsaved UserCart entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            UserCart entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserCart entity);

	/**
	 * Delete a persistent UserCart entity.
	 * 
	 * @param entity
	 *            UserCart entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserCart entity);

	/**
	 * Persist a previously saved UserCart entity and return it or a copy of it
	 * to the sender. A copy of the UserCart entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            UserCart entity to update
	 * @returns UserCart the persisted UserCart entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserCart update(UserCart entity);

	public UserCart findById(UserCartId id);

	/**
	 * Find all UserCart entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserCart property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCart> found by query
	 */
	public List<UserCart> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<UserCart> findByQuantity(Object quantity,
			int... rowStartIdxAndCount);

	public List<UserCart> findByLid(Object lid, int... rowStartIdxAndCount);
	
	public List<UserCart> findByUid(Object uid, int... rowStartIdxAndCount);
	
	public List<UserCart> findByCid(Object cid, int... rowStartIdxAndCount);	

	/**
	 * Find all UserCart entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCart> all UserCart entities
	 */
	public List<UserCart> findAll(int... rowStartIdxAndCount);
}