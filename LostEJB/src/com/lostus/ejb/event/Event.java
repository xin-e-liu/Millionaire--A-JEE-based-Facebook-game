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

package com.lostus.ejb.event;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Event entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "Event", catalog = "LostUS", uniqueConstraints = {})
public class Event extends AbstractEvent implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** minimal constructor */
	public Event(Integer eid) {
		super(eid);
	}

	/** full constructor */
	public Event(Integer eid, String ename, String edesp, Integer efunc,
			Integer ecode, Integer emount) {
		super(eid, ename, edesp, efunc, ecode, emount);
	}

}
