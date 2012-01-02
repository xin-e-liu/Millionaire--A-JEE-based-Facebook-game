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

package com.lostus.ejb.commodity;

import java.util.List;
import javax.ejb.Remote;

/**
 * Remote interface for CommodityFacade.
 * 
 * @author MyEclipse Persistence Tools
 */
@Remote
public interface CommodityFacadeRemote {
	/**
	 * Perform an initial save of a previously unsaved Commodity entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method.
	 * 
	 * @param entity
	 *            Commodity entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Commodity entity);

	/**
	 * Delete a persistent Commodity entity.
	 * 
	 * @param entity
	 *            Commodity entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Commodity entity);

	/**
	 * Persist a previously saved Commodity entity and return it or a copy of it
	 * to the sender. A copy of the Commodity entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity.
	 * 
	 * @param entity
	 *            Commodity entity to update
	 * @returns Commodity the persisted Commodity entity instance, may not be
	 *          the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Commodity update(Commodity entity);

	public Commodity findById(Integer id);

	/**
	 * Find all Commodity entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Commodity property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Commodity> found by query
	 */
	public List<Commodity> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Commodity> findByCname(Object cname, int... rowStartIdxAndCount);

	public List<Commodity> findByCproperty(Object cproperty,
			int... rowStartIdxAndCount);

	public List<Commodity> findByCminPrice(Object cminPrice,
			int... rowStartIdxAndCount);

	public List<Commodity> findByCmaxPrice(Object cmaxPrice,
			int... rowStartIdxAndCount);

	public List<Commodity> findByCmaxQuantity(Object cmaxQuantity,
			int... rowStartIdxAndCount);

	/**
	 * Find all Commodity entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Commodity> all Commodity entities
	 */
	public List<Commodity> findAll(int... rowStartIdxAndCount);
}