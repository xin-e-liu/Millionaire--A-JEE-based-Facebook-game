package com.lostus.ejb.location;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Location entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Location", catalog = "LostUS", uniqueConstraints = {})
public class Location extends AbstractLocation implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Location() {
	}

	/** minimal constructor */
	public Location(Integer lid) {
		super(lid);
	}

	/** full constructor */
	public Location(Integer lid, String lname, Integer ltype, Integer lproperty) {
		super(lid, lname, ltype, lproperty);
	}

}
