package com.lostus.ejb.recommend;

import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for RecommendFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface RecommendFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved Recommend entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Recommend entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Recommend entity);

	/**
	 * Delete a persistent Recommend entity.
	 * 
	 * @param entity
	 *            Recommend entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Recommend entity);

	/**
	 * Persist a previously saved Recommend entity and return it or a copy of it
	 * to the sender. A copy of the Recommend entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Recommend entity to update
	 * @returns Recommend the persisted Recommend entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Recommend update(Recommend entity);

	public Recommend findById(Integer id);

	/**
	 * Find all Recommend entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Recommend property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Recommend> found by query
	 */
	public List<Recommend> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Recommend> findByCsalesAmount(Object csalesAmount,
			int... rowStartIdxAndCount);

	public List<Recommend> findByCscore(Object cscore,
			int... rowStartIdxAndCount);

	/**
	 * Find all Recommend entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Recommend> all Recommend entities
	 */
	public List<Recommend> findAll(int... rowStartIdxAndCount);
}