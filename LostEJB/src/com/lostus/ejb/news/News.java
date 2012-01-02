package com.lostus.ejb.news;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * News entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "LostUS", uniqueConstraints = {})
public class News extends AbstractNews implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(Integer id, Integer uid) {
		super(id, uid);
	}
	
	/** full constructor */
	public News(String content, Integer type, Integer priority,
			Integer uid, Integer idInType, Integer property) {
		super(content, type, priority, uid, idInType, property);
	}

	/** full constructor */
	public News(Integer id, String content, Integer type, Integer priority,
			Integer uid, Integer idInType, Integer property) {
		super(id, content, type, priority, uid, idInType, property);
	}

}
