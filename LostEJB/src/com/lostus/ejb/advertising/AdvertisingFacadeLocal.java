/*
Copyright 2011 codeoedoc

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

package com.lostus.ejb.advertising;

import java.util.List;
import javax.ejb.Local;

/**
 * Local interface for AdvertisingFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Local
public interface AdvertisingFacadeLocal {
	/**
	 * Perform an initial save of a previously unsaved Advertising entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Advertising entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Advertising entity);

	/**
	 * Delete a persistent Advertising entity.
	 * 
	 * @param entity
	 *            Advertising entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Advertising entity);

	/**
	 * Persist a previously saved Advertising entity and return it or a copy of
	 * it to the sender. A copy of the Advertising entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Advertising entity to update
	 * @returns Advertising the persisted Advertising entity instance, may not
	 *          be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Advertising update(Advertising entity);

	public Advertising findById(AdvertisingId id);

	/**
	 * Find all Advertising entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Advertising property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Advertising> found by query
	 */
	public List<Advertising> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Advertising> findByAdlink(Object adlink,
			int... rowStartIdxAndCount);

	public List<Advertising> findByAdimage(Object adimage,
			int... rowStartIdxAndCount);
	
	public List<Advertising> findByAdType(Object adtype,
			int... rowStartIdxAndCount);

	/**
	 * Find all Advertising entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Advertising> all Advertising entities
	 */
	public List<Advertising> findAll(int... rowStartIdxAndCount);
}