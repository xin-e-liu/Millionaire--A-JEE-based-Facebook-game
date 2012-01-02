package com.lostus.ejb.usercart;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserCartId entity provides the base persistence definition of the Id entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class UserCartId implements java.io.Serializable {

	// Fields

	private Integer uid;
	private Integer cid;
	private Integer priceBought;

	// Constructors

	/** default constructor */
	public UserCartId() {
	}

	/** full constructor */
	public UserCartId(Integer uid, Integer cid, Integer priceBought) {
		this.uid = uid;
		this.cid = cid;
		this.priceBought = priceBought;
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

	@Column(name = "PriceBought", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getPriceBought() {
		return this.priceBought;
	}

	public void setPriceBought(Integer priceBought) {
		this.priceBought = priceBought;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserCartId))
			return false;
		UserCartId castOther = (UserCartId) other;

		return ((this.getUid() == castOther.getUid()) || (this.getUid() != null
				&& castOther.getUid() != null && this.getUid().equals(
				castOther.getUid())))
				&& ((this.getCid() == castOther.getCid()) || (this.getCid() != null
						&& castOther.getCid() != null && this.getCid().equals(
						castOther.getCid())))
				&& ((this.getPriceBought() == castOther.getPriceBought()) || (this
						.getPriceBought() != null
						&& castOther.getPriceBought() != null && this
						.getPriceBought().equals(castOther.getPriceBought())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUid() == null ? 0 : this.getUid().hashCode());
		result = 37 * result
				+ (getCid() == null ? 0 : this.getCid().hashCode());
		result = 37
				* result
				+ (getPriceBought() == null ? 0 : this.getPriceBought()
						.hashCode());
		return result;
	}

}