package com.lostus.ejb.usercart;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractUserCart entity provides the base persistence definition of the
 * UserCart entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractUserCart implements java.io.Serializable {

	// Fields

	private UserCartId id;
	private Integer quantity;
	private Integer lid;

	// Constructors

	/** default constructor */
	public AbstractUserCart() {
	}

	/** minimal constructor */
	public AbstractUserCart(UserCartId id) {
		this.id = id;
	}
	
	public AbstractUserCart(UserCartId id, Integer quantity) {
		this.id = id;
		this.quantity = quantity;
	}

	/** full constructor */
	public AbstractUserCart(UserCartId id, Integer quantity, Integer lid) {
		this.id = id;
		this.quantity = quantity;
		this.lid = lid;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "uid", column = @Column(name = "UID", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "cid", column = @Column(name = "CID", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "priceBought", column = @Column(name = "PriceBought", unique = false, nullable = false, insertable = true, updatable = true)) })
	public UserCartId getId() {
		return this.id;
	}

	public void setId(UserCartId id) {
		this.id = id;
	}

	@Column(name = "Quantity", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "LID", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

}