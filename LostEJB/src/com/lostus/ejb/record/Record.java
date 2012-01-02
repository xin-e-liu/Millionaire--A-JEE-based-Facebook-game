package com.lostus.ejb.record;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.sql.Timestamp;
/**
 * Record entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Record", catalog = "LostUS", uniqueConstraints = {})
public class Record extends AbstractRecord implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Record() {
	}

	/** minimal constructor */
	public Record(Integer rid, Date rtimestamp) {
		super(rid, rtimestamp);
	}

	/** full constructor */
	public Record(Integer rid, Integer uid, Integer ucash, Integer udeposit,
			Integer uhealth, Integer ureputation, Integer udebt,
			Date rtimestamp, String uname) {
		super(rid, uid, ucash, udeposit, uhealth, ureputation, udebt,
				rtimestamp, uname);
	}

}
