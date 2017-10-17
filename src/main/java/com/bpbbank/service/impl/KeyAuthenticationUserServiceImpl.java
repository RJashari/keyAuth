package com.bpbbank.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bpbbank.dao.KeyAuthenticationUserDao;
import com.bpbbank.domain.KeyAuthenticationUser;
import com.bpbbank.domain.UserRole;
import com.bpbbank.service.KeyAuthenticationUserService;

@Service("userService")
public class KeyAuthenticationUserServiceImpl implements KeyAuthenticationUserService {

	@Autowired
	KeyAuthenticationUserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		KeyAuthenticationUser kauser = userDao.findUserByUsername(username);
		if(null == kauser) {
			throw new UsernameNotFoundException("No user named " + username + " exists");
		}
		return buildUserForAuthentication(kauser, buildUserAuthority(kauser.getUserRole()));
	}
	
	@Override
	public void saveUser(KeyAuthenticationUser user) {
		userDao.saveUser(user);
	}
	
	@Override
	public void resetUserPassword(String username) {
		userDao.resetUserPassword(username);
	}
	
	@Override
	public void initializeUser(KeyAuthenticationUser user) {
		userDao.initializeUser(user);
	}
	
	@Override
	public List<KeyAuthenticationUser> getAll() {
		return userDao.getAll();
	}
	
	@Override
	public void removeUser(String username) {
		userDao.removeUser(username);
	}
	
	@Override
	public void changeUserStatus(String username) {
		userDao.changeUserStatus(username);
	}
	
	private User buildUserForAuthentication(KeyAuthenticationUser user, List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
		Set<GrantedAuthority> authos = new HashSet<>();
		for(UserRole userRole: userRoles) {
			authos.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		return new ArrayList<>(authos);
	}
}
