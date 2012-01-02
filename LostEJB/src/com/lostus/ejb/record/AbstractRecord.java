package com.lostus.ejb.record;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.sql.Timestamp;

/**
 * AbstractRecord entity provides the base persistence definition of the Record
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRecord implements java.io.Serializable, Comparable {

	// Fields

	private Integer rid;
	private Integer uid;
	private Integer ucash;
	private Integer udeposit;
	private Integer uhealth;
	private Integer ureputation;
	private Integer udebt;
	private Date rtimestamp;
	private String uname;

	// Constructors

	/** default constructor */
	public AbstractRecord() {
	}

	/** minimal constructor */
	public AbstractRecord(Integer rid, Date rtimestamp) {
		this.rid = rid;
		this.rtimestamp = rtimestamp;
	}

	/** full constructor */
	public AbstractRecord(Integer rid, Integer uid, Integer ucash,
			Integer udeposit, Integer uhealth, Integer ureputation,
			Integer udebt, Date rtimestamp, String uname) {
		this.rid = rid;
		this.uid = uid;
		this.ucash = ucash;
		this.udeposit = udeposit;
		this.uhealth = uhealth;
		this.ureputation = ureputation;
		this.udebt = udebt;
		this.rtimestamp = rtimestamp;
		this.uname = uname;
	}

	// Property accessors
	@Id
	@Column(name = "rid", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getRid() {
		return this.rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	@Column(name = "uid", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "ucash", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUcash() {
		return this.ucash;
	}

	public void setUcash(Integer ucash) {
		this.ucash = ucash;
	}

	@Column(name = "udeposit", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUdeposit() {
		return this.udeposit;
	}

	public void setUdeposit(Integer udeposit) {
		this.udeposit = udeposit;
	}

	@Column(name = "uhealth", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUhealth() {
		return this.uhealth;
	}

	public void setUhealth(Integer uhealth) {
		this.uhealth = uhealth;
	}

	@Column(name = "ureputation", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUreputation() {
		return this.ureputation;
	}

	public void setUreputation(Integer ureputation) {
		this.ureputation = ureputation;
	}

	@Column(name = "udebt", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUdebt() {
		return this.udebt;
	}

	public void setUdebt(Integer udebt) {
		this.udebt = udebt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "rtimestamp", unique = false, nullable = false, insertable = true, updatable = true, length = 0)
	public Date getRtimestamp() {
		return this.rtimestamp;
	}

	public void setRtimestamp(Date rtimestamp) {
		this.rtimestamp = rtimestamp;
	}

	@Column(name = "uname", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public int compareTo(Object anotherRecord) throws ClassCastException {
	    if (!(anotherRecord instanceof Record))
	      throw new ClassCastException("A Record object expected.");
	    int anotherRecordMoney = ((Record) anotherRecord).getUcash();  
	    return this.getUcash() - anotherRecordMoney;    
	  }

}