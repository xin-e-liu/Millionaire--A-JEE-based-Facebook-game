package com.lostus.ejb.news;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * AbstractNews entity provides the base persistence definition of the News
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractNews implements java.io.Serializable {

	// Fields

	private Integer id;
	private String content;
	private Integer type;
	private Integer priority;
	private Integer uid;
	private Integer idInType;
	private Integer property;

	// Constructors

	/** default constructor */
	public AbstractNews() {
	}

	/** minimal constructor */
	public AbstractNews(Integer id, Integer uid) {
		this.id = id;
		this.uid = uid;
	}
	
	public AbstractNews(String content, Integer type,
			Integer priority, Integer uid, Integer idInType, Integer property) {
		this.content = content;
		this.type = type;
		this.priority = priority;
		this.uid = uid;
		this.idInType = idInType;
		this.property = property;
	}

	/** full constructor */
	public AbstractNews(Integer id, String content, Integer type,
			Integer priority, Integer uid, Integer idInType, Integer property) {
		this.id = id;
		this.content = content;
		this.type = type;
		this.priority = priority;
		this.uid = uid;
		this.idInType = idInType;
		this.property = property;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "content", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "priority", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	@Column(name = "uid", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "idInType", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getIdInType() {
		return this.idInType;
	}

	public void setIdInType(Integer idInType) {
		this.idInType = idInType;
	}

	@Column(name = "property", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getProperty() {
		return this.property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

}