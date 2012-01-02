package com.lostus.ejb.recommend;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractRecommend entity provides the base persistence definition of the
 * Recommend entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractRecommend implements java.io.Serializable {

	// Fields

	private Integer cid;
	private Integer csalesAmount;
	private Integer cscore;

	// Constructors

	/** default constructor */
	public AbstractRecommend() {
	}

	/** minimal constructor */
	public AbstractRecommend(Integer cid) {
		this.cid = cid;
	}

	/** full constructor */
	public AbstractRecommend(Integer cid, Integer csalesAmount, Integer cscore) {
		this.cid = cid;
		this.csalesAmount = csalesAmount;
		this.cscore = cscore;
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

	@Column(name = "CSalesAmount", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCsalesAmount() {
		return this.csalesAmount;
	}

	public void setCsalesAmount(Integer csalesAmount) {
		this.csalesAmount = csalesAmount;
	}

	@Column(name = "CScore", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCscore() {
		return this.cscore;
	}

	public void setCscore(Integer cscore) {
		this.cscore = cscore;
	}

}