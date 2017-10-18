package com.bpbbank.dao;

import java.util.List;

import com.bpbbank.domain.KeyAuthenticationUser;

public interface KeyAuthenticationUserDao {

	KeyAuthenticationUser findUserByUsername(String username);
	
	void saveUser(KeyAuthenticationUser user);
	
	void initializeUser(KeyAuthenticationUser user);
	
	void resetUserPassword(String username);
	
	List<KeyAuthenticationUser> getAll();
	
	void removeUser(String username);
	
	void changeUserStatus(String username);
	
	boolean comparePassword(String password, String confirmPassword);
}
