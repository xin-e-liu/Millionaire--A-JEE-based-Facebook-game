package com.lostus.ejb.news;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for NewsFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface NewsFacadeRemote {
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
	public void save(News entity);

	/**
	 * Delete a persistent News entity.
	 * 
	 * @param entity
	 *            News entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(News entity);

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
	public News update(News entity);

	public News findById(Integer id);

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
	 *            count of results to return.
	 * @return List<News> found by query
	 */
	public List<News> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<News> findByContent(Object content, int... rowStartIdxAndCount);

	public List<News> findByType(Object type, int... rowStartIdxAndCount);

	public List<News> findByPriority(Object priority,
			int... rowStartIdxAndCount);

	public List<News> findByUid(Object uid, int... rowStartIdxAndCount);

	public List<News> findByIdInType(Object idInType,
			int... rowStartIdxAndCount);

	public List<News> findByProperty(Object property,
			int... rowStartIdxAndCount);

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
	public List<News> findAll(int... rowStartIdxAndCount);
}