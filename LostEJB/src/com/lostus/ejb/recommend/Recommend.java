package com.lostus.ejb.recommend;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Recommend entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Recommend", catalog = "LostUS", uniqueConstraints = {})
public class Recommend extends AbstractRecommend implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Recommend() {
	}

	/** minimal constructor */
	public Recommend(Integer cid) {
		super(cid);
	}

	/** full constructor */
	public Recommend(Integer cid, Integer csalesAmount, Integer cscore) {
		super(cid, csalesAmount, cscore);
	}

}
