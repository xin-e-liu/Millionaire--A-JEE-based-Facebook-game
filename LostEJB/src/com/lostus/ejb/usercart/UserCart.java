package com.lostus.ejb.usercart;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserCart entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "UserCart", catalog = "LostUS", uniqueConstraints = {})
public class UserCart extends AbstractUserCart implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public UserCart() {
	}

	/** minimal constructor */
	public UserCart(UserCartId id) {
		super(id);
	}
	
	public UserCart(UserCartId id, Integer quantity) {
		super(id, quantity);
	}

	/** full constructor */
	public UserCart(UserCartId id, Integer quantity, Integer lid) {
		super(id, quantity, lid);
	}

}
