package com.lostus.ejb.shopping;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Shopping entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Shopping", catalog = "LostUS", uniqueConstraints = {})
public class Shopping extends AbstractShopping implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Shopping() {
	}

	/** minimal constructor */
	public Shopping(ShoppingId id) {
		super(id);
	}
	
	public Shopping(ShoppingId id, Integer cquantity) {
		super(id, cquantity);
	}

	/** full constructor */
	public Shopping(ShoppingId id, Integer lid, Integer cprice,
			Integer cquantity) {
		super(id, lid, cprice, cquantity);
	}

}
