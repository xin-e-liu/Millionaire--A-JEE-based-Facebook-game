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

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AdvertisingId entity provides the base persistence definition of the ingId
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Embeddable
public class AdvertisingId implements java.io.Serializable {

	// Fields

	private Integer adtype;
	private Integer adid;
	private Integer property;

	// Constructors

	/** default constructor */
	public AdvertisingId() {
	}

	/** full constructor */
	public AdvertisingId(Integer adtype, Integer adid, Integer property) {
		this.adtype = adtype;
		this.adid = adid;
		this.property = property;
	}

	// Property accessors

	@Column(name = "adtype", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getAdtype() {
		return this.adtype;
	}

	public void setAdtype(Integer adtype) {
		this.adtype = adtype;
	}

	@Column(name = "adid", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getAdid() {
		return this.adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	@Column(name = "property", unique = false, nullable = false, insertable = true, updatable = true)
	public Integer getProperty() {
		return this.property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AdvertisingId))
			return false;
		AdvertisingId castOther = (AdvertisingId) other;

		return ((this.getAdtype() == castOther.getAdtype()) || (this
				.getAdtype() != null
				&& castOther.getAdtype() != null && this.getAdtype().equals(
				castOther.getAdtype())))
				&& ((this.getAdid() == castOther.getAdid()) || (this.getAdid() != null
						&& castOther.getAdid() != null && this.getAdid()
						.equals(castOther.getAdid())))
				&& ((this.getProperty() == castOther.getProperty()) || (this
						.getProperty() != null
						&& castOther.getProperty() != null && this
						.getProperty().equals(castOther.getProperty())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getAdtype() == null ? 0 : this.getAdtype().hashCode());
		result = 37 * result
				+ (getAdid() == null ? 0 : this.getAdid().hashCode());
		result = 37 * result
				+ (getProperty() == null ? 0 : this.getProperty().hashCode());
		return result;
	}

}