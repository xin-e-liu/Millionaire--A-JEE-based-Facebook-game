package com.lostus.ejb.location;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractLocation entity provides the base persistence definition of the
 * Location entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractLocation implements java.io.Serializable {

	// Fields

	private Integer lid;
	private String lname;
	private Integer ltype;
	private Integer lproperty;

	// Constructors

	/** default constructor */
	public AbstractLocation() {
	}

	/** minimal constructor */
	public AbstractLocation(Integer lid) {
		this.lid = lid;
	}

	/** full constructor */
	public AbstractLocation(Integer lid, String lname, Integer ltype,
			Integer lproperty) {
		this.lid = lid;
		this.lname = lname;
		this.ltype = ltype;
		this.lproperty = lproperty;
	}

	// Property accessors
	@Id
	@Column(name = "LID", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	@Column(name = "LName", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Column(name = "LType", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLtype() {
		return this.ltype;
	}

	public void setLtype(Integer ltype) {
		this.ltype = ltype;
	}

	@Column(name = "LProperty", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLproperty() {
		return this.lproperty;
	}

	public void setLproperty(Integer lproperty) {
		this.lproperty = lproperty;
	}

}