package com.bpbbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @Value("${ad.domain}")
	private String AD_DOMAIN;

	// @Value("${ad.url}")
	private String AD_URL;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("Partin Halimi").password("Banka123!").roles("USER").and()
				.withUser("rinori").password("Banka123!").roles("USER").and().withUser("gagi").password("Banka123!")
				.roles("USER").and().withUser("r").password("r")
				.roles("USER");
	}
} 