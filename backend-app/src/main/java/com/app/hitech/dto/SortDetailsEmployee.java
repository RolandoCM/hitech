package com.app.hitech.dto;

import java.io.Serializable;
import java.util.Set;

import com.app.hitech.entities.Role;

/**
 * @author casti
 *
 */
public class SortDetailsEmployee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7728846893624317451L;
	private String code;
	private String name;
	private boolean enabled;
	private Set<Role> roles;

	public SortDetailsEmployee() {
	}

	public SortDetailsEmployee withCode(String code) {
		this.code = code;
		return this;
	}

	public SortDetailsEmployee withName(String name) {
		this.name = name;
		return this;
	}

	public SortDetailsEmployee roles(Set<Role> roles) {
		this.roles = roles;
		return this;
	}

	public SortDetailsEmployee enabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public SortDetailsEmployee build() {
		SortDetailsEmployee details = new SortDetailsEmployee();
		details.code = this.code;
		details.name = this.name;
		details.roles = this.roles;
		details.enabled = this.enabled;
		return details;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
