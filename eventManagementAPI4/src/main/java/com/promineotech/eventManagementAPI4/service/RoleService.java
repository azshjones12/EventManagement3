package com.promineotech.eventManagementAPI4.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.eventManagementAPI4.entity.Role;
import com.promineotech.eventManagementAPI4.entity.UserAccount;
import com.promineotech.eventManagementAPI4.repository.UserAccountRepository;

@Service
public class RoleService {
	
	@Autowired
	private UserAccountRepository userRepo;
	
//finds role(s) of given user based on userId
	public List<Role> getUserRoles(Long userId) {
		UserAccount foundUserAccount = userRepo.findOne(userId);
		if(foundUserAccount !=null) {	
		foundUserAccount.getRoles();
		}
		return foundUserAccount.getRoles();

}

}
