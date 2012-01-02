package com.lostus.ejb.shopping;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ShoppingId entity provides the base persistence definition of the Id entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class ShoppingId implements java.io.Serializable {

	// Fields

	private Integer uid;
	private Integer cid;

	// Constructors

	/** default constructor */
	public ShoppingId() {
	}

	/** full constructor */
	public ShoppingId(Integer uid, Integer cid) {
		this.uid = uid;
		this.cid = cid;
	}

	// Property accessors

	@Column(name = "UID", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	@Column(name = "CID", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ShoppingId))
			return false;
		ShoppingId castOther = (ShoppingId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		return result;
	}

}