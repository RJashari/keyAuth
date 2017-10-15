package com.bpbbank.dao;

import org.springframework.stereotype.Repository;

import com.bpbbank.domain.KeyAuthenticationUser;

public interface KeyAuthenticationUserDao {

	KeyAuthenticationUser findUserByUsername(String username);
	
	void saveUser(KeyAuthenticationUser user);
}
