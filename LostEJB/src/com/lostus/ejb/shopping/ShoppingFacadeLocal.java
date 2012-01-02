package com.lostus.ejb.shopping;

import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for ShoppingFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface ShoppingFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved Shopping entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Shopping entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Shopping entity);

	/**
	 * Delete a persistent Shopping entity.
	 * 
	 * @param entity
	 *            Shopping entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Shopping entity);

	/**
	 * Persist a previously saved Shopping entity and return it or a copy of it
	 * to the sender. A copy of the Shopping entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Shopping entity to update
	 * @returns Shopping the persisted Shopping entity instance, may not be the
	 *          same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Shopping update(Shopping entity);

	public Shopping findById(ShoppingId id);

	/**
	 * Find all Shopping entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Shopping property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Shopping> found by query
	 */
	public List<Shopping> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Shopping> findByLid(Object lid, int... rowStartIdxAndCount);

	public List<Shopping> findByCprice(Object cprice,
			int... rowStartIdxAndCount);

	public List<Shopping> findByCquantity(Object cquantity,
			int... rowStartIdxAndCount);
	
	public List<Shopping> findByUid(Object uid,
			int... rowStartIdxAndCount);

	/**
	 * Find all Shopping entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Shopping> all Shopping entities
	 */
	public List<Shopping> findAll(int... rowStartIdxAndCount);
}