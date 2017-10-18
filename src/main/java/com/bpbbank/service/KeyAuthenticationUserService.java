package com.bpbbank.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bpbbank.domain.KeyAuthenticationUser;

public interface KeyAuthenticationUserService extends UserDetailsService {

	void saveUser(KeyAuthenticationUser user);
	
	void initializeUser(KeyAuthenticationUser user);
	
	void resetUserPassword(String username);
	
	List<KeyAuthenticationUser> getAll();
	
	void removeUser(String username);
	
	void changeUserStatus(String username);
	
	boolean comparePassword(String password, String confirmPassword);
}
