package com.bpbbank.events;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.bpbbank.domain.KeyAuthenticationUser;
import com.bpbbank.domain.UserRole;
import com.bpbbank.domain.UserRoleNames;
import com.bpbbank.service.impl.KeyAuthenticationUserServiceImpl;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	KeyAuthenticationUserServiceImpl service;
	private final Logger LOGGER = Logger.getLogger(ApplicationStartup.class);
	
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		try {
			service.loadUserByUsername("admin");
		}
		catch (UsernameNotFoundException exception) {
			LOGGER.info("Meqe useri admin nuk ekzistuaka ne aplikacion, po e inicializoj duke e futur te ri ne databaze");
			KeyAuthenticationUser user = new KeyAuthenticationUser("admin", "$2a$10$5kCKO/IAcqcrAy0IzrHFK.kEVBeBKVn8j/m4xcN7TTBhb1RJ3GJ7S", true);
			UserRole userRole = new UserRole(user, UserRoleNames.ADMIN.name());
			user.getUserRole().add(userRole);
			user.setEmail("rinor.jashari@bpbbank.com");//me properties
			service.saveUser(user);
			
		}
	}

}