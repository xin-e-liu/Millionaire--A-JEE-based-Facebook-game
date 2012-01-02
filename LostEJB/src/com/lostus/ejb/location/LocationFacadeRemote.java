package com.lostus.ejb.location;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for LocationFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface LocationFacadeRemote {
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
	public void save(Location entity);

	/**
	 * Delete a persistent Location entity.
	 * 
	 * @param entity
	 *            Location entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Location entity);

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
	public Location update(Location entity);

	public Location findById(Integer id);

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
	 *            count of results to return.
	 * @return List<Location> found by query
	 */
	public List<Location> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Location> findByLname(Object lname, int... rowStartIdxAndCount);

	public List<Location> findByLtype(Object ltype, int... rowStartIdxAndCount);

	public List<Location> findByLproperty(Object lproperty,
			int... rowStartIdxAndCount);

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
	public List<Location> findAll(int... rowStartIdxAndCount);
}