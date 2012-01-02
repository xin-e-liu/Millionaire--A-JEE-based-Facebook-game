package com.lostus.ejb.user;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * User entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "User", catalog = "LostUS", uniqueConstraints = {})
public class User extends AbstractUser implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(Integer uid) {
		super(uid);
	}
	
	public User(Integer uid, String sessionId) {
		super(uid, sessionId);
	}

	/** full constructor */
	public User(Integer uid, String uname, Integer umoney, Integer uttl,
			Integer ucapacity, String sessionId, Integer udeposit,
			Integer udebt, Integer uhealth, Integer ureputation) {
		super(uid, uname, umoney, uttl, ucapacity, sessionId, udeposit, udebt,
				uhealth, ureputation);
	}

}
