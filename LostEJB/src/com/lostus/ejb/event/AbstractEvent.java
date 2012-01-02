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

package com.lostus.ejb.event;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractEvent entity provides the base persistence definition of the Event
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractEvent implements java.io.Serializable {

	// Fields

	private Integer eid;
	private String ename;
	private String edesp;
	private Integer efunc;
	private Integer ecode;
	private Integer emount;

	// Constructors

	/** default constructor */
	public AbstractEvent() {
	}

	/** minimal constructor */
	public AbstractEvent(Integer eid) {
		this.eid = eid;
	}

	/** full constructor */
	public AbstractEvent(Integer eid, String ename, String edesp,
			Integer efunc, Integer ecode, Integer emount) {
		this.eid = eid;
		this.ename = ename;
		this.edesp = edesp;
		this.efunc = efunc;
		this.ecode = ecode;
		this.emount = emount;
	}

	// Property accessors
	@Id
	@Column(name = "EID", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getEid() {
		return this.eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	@Column(name = "EName", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "EDesp", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getEdesp() {
		return this.edesp;
	}

	public void setEdesp(String edesp) {
		this.edesp = edesp;
	}

	@Column(name = "EFunc", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getEfunc() {
		return this.efunc;
	}

	public void setEfunc(Integer efunc) {
		this.efunc = efunc;
	}

	@Column(name = "ECode", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getEcode() {
		return this.ecode;
	}

	public void setEcode(Integer ecode) {
		this.ecode = ecode;
	}

	@Column(name = "EMount", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getEmount() {
		return this.emount;
	}

	public void setEmount(Integer emount) {
		this.emount = emount;
	}

}