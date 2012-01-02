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

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Commodity entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Commodity", catalog = "LostUS", uniqueConstraints = {})
public class Commodity extends AbstractCommodity implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Commodity() {
	}

	/** minimal constructor */
	public Commodity(Integer cid) {
		super(cid);
	}

	/** full constructor */
	public Commodity(Integer cid, String cname, Integer cproperty,
			Integer cminPrice, Integer cmaxPrice, Integer cmaxQuantity) {
		super(cid, cname, cproperty, cminPrice, cmaxPrice, cmaxQuantity);
	}

}
