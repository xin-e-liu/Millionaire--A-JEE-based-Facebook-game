package com.lostus.ejb.record;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for RecordFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface RecordFacadeRemote {
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
	public void save(Record entity);

	/**
	 * Delete a persistent Record entity.
	 * 
	 * @param entity
	 *            Record entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Record entity);

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
	public Record update(Record entity);

	public Record findById(Integer id);

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
	 *            count of results to return.
	 * @return List<Record> found by query
	 */
	public List<Record> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Record> findByUid(Object uid, int... rowStartIdxAndCount);

	public List<Record> findByUcash(Object ucash, int... rowStartIdxAndCount);

	public List<Record> findByUdeposit(Object udeposit,
			int... rowStartIdxAndCount);

	public List<Record> findByUhealth(Object uhealth,
			int... rowStartIdxAndCount);

	public List<Record> findByUreputation(Object ureputation,
			int... rowStartIdxAndCount);

	public List<Record> findByUdebt(Object udebt, int... rowStartIdxAndCount);

	public List<Record> findByUname(Object uname, int... rowStartIdxAndCount);

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
	public List<Record> findAll(int... rowStartIdxAndCount);
}