package com.promineotech.eventManagementAPI4.controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.eventManagementAPI4.entity.Credentials;
import com.promineotech.eventManagementAPI4.entity.UserAccount;
import com.promineotech.eventManagementAPI4.service.AuthService;

@RestController
@RequestMapping("/users")
public class UserAccountController {
	
	@Autowired
	private AuthService authService;

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Object> register(@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.register(cred), HttpStatus.CREATED);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody Credentials cred) {
		try {
			return new ResponseEntity<Object>(authService.login(cred), HttpStatus.OK);
		} catch (AuthenticationException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@RequestMapping("/{userId}")
	public UserAccount getUserAccount(@PathVariable Long userId) {
		return authService.getUserAccount(userId);
	}
	
	@RequestMapping(value ="/{userId}", method=RequestMethod.PUT)
	public UserAccount updateUserAccount(@PathVariable Long userId, @RequestBody UserAccount user) {
		return authService.updateUserAccount(userId, user);
	}
	
	@RequestMapping(value ="/password/{userId}", method=RequestMethod.PUT)
	public UserAccount changePassword(@PathVariable Long userId, @RequestBody Credentials cred) {
		return authService.changePassword(cred, userId);
	}
	
//	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
//	public void deleteUserAccount(@PathVariable Long id) {
//		authService.deleteUserAccount(id);
//	}

	@RequestMapping(value="/{userId}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUserAccount(@PathVariable Long userId) {
		try {
		authService.deleteUserAccount(userId);
		return new ResponseEntity<Object>("Successfully deleted user with user ID: " + userId, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<Object>("Unable to delete user.", HttpStatus.BAD_REQUEST);
	}
}
}

