package com.promineotech.eventManagementAPI4.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.promineotech.eventManagementAPI4.entity.Credentials;
import com.promineotech.eventManagementAPI4.entity.UserAccount;
import com.promineotech.eventManagementAPI4.repository.UserAccountRepository;

@Service
public class AuthService {

	@Autowired
	private UserAccountRepository userRepo;
	
	public UserAccount register(Credentials cred) throws AuthenticationException {
		UserAccount user = new UserAccount();
		user.setUsername(cred.getUsername());
		user.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		try {
			userRepo.save(user);
			return user;
		} catch (DataIntegrityViolationException e) {
			throw new AuthenticationException("Username not available.");
		}	
	}
	
	public UserAccount login(Credentials cred) throws AuthenticationException {
		UserAccount foundUser = userRepo.findByUsername(cred.getUsername());
		if (foundUser != null && BCrypt.checkpw(cred.getPassword(), foundUser.getHash())) {
				return foundUser;
		}
		throw new AuthenticationException("Incorrect username or password.");
		
	}
	
	//finds user with the given id in the DB
	public UserAccount getUserAccount(Long id) {
		return userRepo.findOne(id);
}
	
	public Iterable<UserAccount> getUserAccounts(){
		return userRepo.findAll();
	}
	
	public void deleteUserAccount(Long id) {
		userRepo.delete(id);
	}
	
	public UserAccount updateUserAccount(Long id, UserAccount user) {
		UserAccount foundUserAccount = userRepo.findOne(id);
		if(foundUserAccount != null) {
			foundUserAccount.setFirstName(user.getFirstName());
			foundUserAccount.setLastName(user.getLastName());
			foundUserAccount.setEmail(user.getEmail());
			foundUserAccount.setPhoneNumber(user.getPhoneNumber());
			foundUserAccount.setUsername(user.getUsername());
			foundUserAccount.setRoles(user.getRoles());
			userRepo.save(foundUserAccount);
		}
		return foundUserAccount;
	}
	
	public UserAccount changePassword(Credentials cred, Long id) {
		UserAccount foundUserAccount = userRepo.findOne(id);
		if(foundUserAccount !=null) {
			foundUserAccount.setHash(BCrypt.hashpw(cred.getPassword(), BCrypt.gensalt()));
		userRepo.save(foundUserAccount);
		}
		return foundUserAccount;
}

}