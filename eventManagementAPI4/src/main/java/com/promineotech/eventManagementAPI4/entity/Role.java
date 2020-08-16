package com.promineotech.eventManagementAPI4.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.promineotech.eventManagementAPI4.util.RoleType;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	private RoleType roles;
	
	@ManyToMany(mappedBy="roles", cascade = CascadeType.MERGE)
	@JsonIgnore
	private Set<UserAccount> users;

    public Set<UserAccount> getUsers() { 
    	return users; 
    	}

	public RoleType getRoles() {
		return roles;
	}

	public void setRoles(RoleType roles) {
		this.roles = roles;
	}


}
