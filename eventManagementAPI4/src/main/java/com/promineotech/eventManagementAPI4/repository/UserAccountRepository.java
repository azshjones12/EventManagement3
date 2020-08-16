package com.promineotech.eventManagementAPI4.repository;

import org.springframework.data.repository.CrudRepository;

import com.promineotech.eventManagementAPI4.entity.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long>  {
	
	public UserAccount findByUsername(String username);

}
