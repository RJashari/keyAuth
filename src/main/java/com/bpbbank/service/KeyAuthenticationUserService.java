package com.bpbbank.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bpbbank.domain.KeyAuthenticationUser;

public interface KeyAuthenticationUserService extends UserDetailsService {

	void saveUser(KeyAuthenticationUser user);
}
