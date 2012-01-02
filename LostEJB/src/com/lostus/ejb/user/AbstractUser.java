package com.lostus.ejb.user;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractUser entity provides the base persistence definition of the User
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUser implements java.io.Serializable {

	// Fields

	private Integer uid;
	private String uname;
	private Integer umoney;
	private Integer uttl;
	private Integer ucapacity;
	private String sessionId;
	private Integer udeposit;
	private Integer udebt;
	private Integer uhealth;
	private Integer ureputation;

	// Constructors

	/** default constructor */
	public AbstractUser() {
	}

	/** minimal constructor */
	public AbstractUser(Integer uid) {
		this.uid = uid;
	}
	
	public AbstractUser(Integer uid, String sessionId) {
		this.uid = uid;
		this.sessionId = sessionId;
	}

	/** full constructor */
	public AbstractUser(Integer uid, String uname, Integer umoney,
			Integer uttl, Integer ucapacity, String sessionId,
			Integer udeposit, Integer udebt, Integer uhealth,
			Integer ureputation) {
		this.uid = uid;
		this.uname = uname;
		this.umoney = umoney;
		this.uttl = uttl;
		this.ucapacity = ucapacity;
		this.sessionId = sessionId;
		this.udeposit = udeposit;
		this.udebt = udebt;
		this.uhealth = uhealth;
		this.ureputation = ureputation;
	}

	// Property accessors
	@Id
	@Column(name = "UID", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "UName", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "UMoney", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUmoney() {
		return this.umoney;
	}

	public void setUmoney(Integer umoney) {
		this.umoney = umoney;
	}

	@Column(name = "UTTL", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUttl() {
		return this.uttl;
	}

	public void setUttl(Integer uttl) {
		this.uttl = uttl;
	}

	@Column(name = "UCapacity", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUcapacity() {
		return this.ucapacity;
	}

	public void setUcapacity(Integer ucapacity) {
		this.ucapacity = ucapacity;
	}

	@Column(name = "SessionID", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "UDeposit", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUdeposit() {
		return this.udeposit;
	}

	public void setUdeposit(Integer udeposit) {
		this.udeposit = udeposit;
	}

	@Column(name = "UDebt", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUdebt() {
		return this.udebt;
	}

	public void setUdebt(Integer udebt) {
		this.udebt = udebt;
	}

	@Column(name = "UHealth", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUhealth() {
		return this.uhealth;
	}

	public void setUhealth(Integer uhealth) {
		this.uhealth = uhealth;
	}

	@Column(name = "UReputation", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getUreputation() {
		return this.ureputation;
	}

	public void setUreputation(Integer ureputation) {
		this.ureputation = ureputation;
	}

}