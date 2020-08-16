package com.promineotech.eventManagementAPI4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.eventManagementAPI4.entity.Role;
import com.promineotech.eventManagementAPI4.service.RoleService;

@RestController
@RequestMapping("/users/{userId}/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Role> getUserRoles(@PathVariable Long userId) {
		return roleService.getUserRoles(userId);
	}


}
