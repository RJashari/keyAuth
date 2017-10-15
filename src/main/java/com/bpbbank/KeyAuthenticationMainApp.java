package com.bpbbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KeyAuthenticationMainApp {

	@Autowired
	private ApplicationContext applicationContext;

	public KeyAuthenticationMainApp(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		for(String s: this.applicationContext.getBeanDefinitionNames()) {
			System.out.println("bean definition name: " + s);
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("password00: " + new BCryptPasswordEncoder().encode("password00"));
		SpringApplication.run(KeyAuthenticationMainApp.class, args);

	}

}