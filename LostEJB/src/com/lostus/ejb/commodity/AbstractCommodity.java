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

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractCommodity entity provides the base persistence definition of the
 * Commodity entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractCommodity implements java.io.Serializable {

	// Fields

	private Integer cid;
	private String cname;
	private Integer cproperty;
	private Integer cminPrice;
	private Integer cmaxPrice;
	private Integer cmaxQuantity;

	// Constructors

	/** default constructor */
	public AbstractCommodity() {
	}

	/** minimal constructor */
	public AbstractCommodity(Integer cid) {
		this.cid = cid;
	}

	/** full constructor */
	public AbstractCommodity(Integer cid, String cname, Integer cproperty,
			Integer cminPrice, Integer cmaxPrice, Integer cmaxQuantity) {
		this.cid = cid;
		this.cname = cname;
		this.cproperty = cproperty;
		this.cminPrice = cminPrice;
		this.cmaxPrice = cmaxPrice;
		this.cmaxQuantity = cmaxQuantity;
	}

	// Property accessors
	@Id
	@Column(name = "CID", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "CName", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "CProperty", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCproperty() {
		return this.cproperty;
	}

	public void setCproperty(Integer cproperty) {
		this.cproperty = cproperty;
	}

	@Column(name = "CMinPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCminPrice() {
		return this.cminPrice;
	}

	public void setCminPrice(Integer cminPrice) {
		this.cminPrice = cminPrice;
	}

	@Column(name = "CMaxPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCmaxPrice() {
		return this.cmaxPrice;
	}

	public void setCmaxPrice(Integer cmaxPrice) {
		this.cmaxPrice = cmaxPrice;
	}

	@Column(name = "CMaxQuantity", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCmaxQuantity() {
		return this.cmaxQuantity;
	}

	public void setCmaxQuantity(Integer cmaxQuantity) {
		this.cmaxQuantity = cmaxQuantity;
	}

}