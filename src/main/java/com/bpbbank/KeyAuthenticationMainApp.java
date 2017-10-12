package com.bpbbank;

import javax.annotation.PostConstruct;
import javax.naming.Context;

import org.springframework.beans.factory.annotation.Autowired;
//import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

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
		SpringApplication.run(KeyAuthenticationMainApp.class, args);

	}

}