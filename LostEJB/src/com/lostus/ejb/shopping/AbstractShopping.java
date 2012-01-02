package com.lostus.ejb.shopping;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractShopping entity provides the base persistence definition of the
 * Shopping entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractShopping implements java.io.Serializable {

	// Fields

	private ShoppingId id;
	private Integer lid;
	private Integer cprice;
	private Integer cquantity;

	// Constructors

	/** default constructor */
	public AbstractShopping() {
	}
	
	public AbstractShopping(ShoppingId id, Integer cquantity){
		this.id=id;
		this.cquantity=cquantity;
	}

	/** minimal constructor */
	public AbstractShopping(ShoppingId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractShopping(ShoppingId id, Integer lid, Integer cprice,
			Integer cquantity) {
		this.id = id;
		this.lid = lid;
		this.cprice = cprice;
		this.cquantity = cquantity;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "uid", column = @Column(name = "UID", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "cid", column = @Column(name = "CID", unique = false, nullable = false, insertable = true, updatable = true)) })
	public ShoppingId getId() {
		return this.id;
	}

	public void setId(ShoppingId id) {
		this.id = id;
	}

	@Column(name = "LID", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getLid() {
		return this.lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	@Column(name = "CPrice", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCprice() {
		return this.cprice;
	}

	public void setCprice(Integer cprice) {
		this.cprice = cprice;
	}

	@Column(name = "CQuantity", unique = false, nullable = true, insertable = true, updatable = true)
	public Integer getCquantity() {
		return this.cquantity;
	}

	public void setCquantity(Integer cquantity) {
		this.cquantity = cquantity;
	}

}