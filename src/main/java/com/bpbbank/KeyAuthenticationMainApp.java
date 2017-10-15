package com.bpbbank;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.apache.xbean.spring.context.FileSystemXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class KeyAuthenticationMainApp {

	@Autowired
	@Qualifier(value = "dataSource")
	DataSource dataSource;
	
	@Autowired
	private ApplicationContext applicationContext;

	public KeyAuthenticationMainApp(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		for(String s: this.applicationContext.getBeanDefinitionNames()) {
			System.out.println("bean definition name: " + s);
		}
	}
	
	public DataSource dataSource() {
		return null;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(KeyAuthenticationMainApp.class, args);

	}

}