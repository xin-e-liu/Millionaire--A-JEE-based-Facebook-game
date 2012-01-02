/*
Copyright 2011 codeoedoc

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.lostus.ejb.advertising;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

/**
 * AbstractAdvertising entity provides the base persistence definition of the
 * Advertising entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@MappedSuperclass
public abstract class AbstractAdvertising implements java.io.Serializable {

	// Fields

	private AdvertisingId id;
	private String adlink;
	private String adimage;

	// Constructors

	/** default constructor */
	public AbstractAdvertising() {
	}

	/** minimal constructor */
	public AbstractAdvertising(AdvertisingId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractAdvertising(AdvertisingId id, String adlink, String adimage) {
		this.id = id;
		this.adlink = adlink;
		this.adimage = adimage;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides( {
			@AttributeOverride(name = "adtype", column = @Column(name = "adtype", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "adid", column = @Column(name = "adid", unique = false, nullable = false, insertable = true, updatable = true)),
			@AttributeOverride(name = "property", column = @Column(name = "property", unique = false, nullable = false, insertable = true, updatable = true)) })
	public AdvertisingId getId() {
		return this.id;
	}

	public void setId(AdvertisingId id) {
		this.id = id;
	}

	@Column(name = "adlink", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getAdlink() {
		return this.adlink;
	}

	public void setAdlink(String adlink) {
		this.adlink = adlink;
	}

	@Column(name = "adimage", unique = false, nullable = true, insertable = true, updatable = true, length = 65535)
	public String getAdimage() {
		return this.adimage;
	}

	public void setAdimage(String adimage) {
		this.adimage = adimage;
	}

}