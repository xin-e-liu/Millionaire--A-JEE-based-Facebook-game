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

package com.lostus.ejb.event;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for EventFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface EventFacadeRemote {
	/**
	 * Perform an initial save of a previously unsaved Event entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Event entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Event entity);

	/**
	 * Delete a persistent Event entity.
	 * 
	 * @param entity
	 *            Event entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Event entity);

	/**
	 * Persist a previously saved Event entity and return it or a copy of it to
	 * the sender. A copy of the Event entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity.
	 * 
	 * @param entity
	 *            Event entity to update
	 * @returns Event the persisted Event entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Event update(Event entity);

	public Event findById(Integer id);

	/**
	 * Find all Event entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Event property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Event> found by query
	 */
	public List<Event> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Event> findByEname(Object ename, int... rowStartIdxAndCount);

	public List<Event> findByEdesp(Object edesp, int... rowStartIdxAndCount);

	public List<Event> findByEfunc(Object efunc, int... rowStartIdxAndCount);

	public List<Event> findByEcode(Object ecode, int... rowStartIdxAndCount);

	public List<Event> findByEmount(Object emount, int... rowStartIdxAndCount);

	/**
	 * Find all Event entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Event> all Event entities
	 */
	public List<Event> findAll(int... rowStartIdxAndCount);
}