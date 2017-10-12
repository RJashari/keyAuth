package com.bpbbank;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// @Value("${ad.domain}")
	private String AD_DOMAIN = "LDAP://DC=bpb,DC=com";

	// @Value("${ad.url}")
	private String AD_URL = "ldap://hq-dc01.bpb.com:389/dc=bpb,dc=com";
/*
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
	*/
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().fullyAuthenticated()
				.and()
			.formLogin();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.ldapAuthentication()
//			.userSearchBase(",DC=bpb,DC=com")
//			.userDnPatterns("CN={0},OU=Sektori i Databazes dhe zhvillimit,OU=Departamenti i IT,OU=HQ Manage Users,OU=Managed Users,DC=bpb,DC=com")
			.userDnPatterns("CN={0},OU=Sektori i Databazes dhe zhvillimit,OU=Departamenti i IT,OU=HQ Manage Users,OU=Managed Users")
			.contextSource()
				.managerDn("bpb\\serviceadmin")
				.managerPassword("BpB123456!")
//				.url("ldap://hq-dc01.bpb.com:389")
				.url("ldap://192.178.10.10:3268/dc=bpb,dc=com")
				.and()
			.passwordCompare()
//				.passwordEncoder(new LdapShaPasswordEncoder())
//				.passwordAttribute("userPassword");
				.passwordEncoder(new PlaintextPasswordEncoder())
				.passwordAttribute("mailNickname");
//		.passwordAttribute("mailNickname");
	}
	
	
	private String adServer = "hq-dc01.bpb.com";

//	@Value("${app.service-principal}")
	private String servicePrincipal;

//	@Value("${app.keytab-location}")
	private String keytabLocation;

//	@Value("${app.ldap-search-base}")
	private String ldapSearchBase = "dc=bpb,dc=com";

//	@Value("${app.ldap-search-filter}")
	private String ldapSearchFilter = "(| (userPrincipalName={0}) (sAMAccountName={0}))";
	
/*	@Bean
	public KerberosLdapContextSource kerberosLdapContextSource() {
	    KerberosLdapContextSource contextSource = new KerberosLdapContextSource(adServer);
	    SunJaasKrb5LoginConfig loginConfig = new SunJaasKrb5LoginConfig();
	    loginConfig.setKeyTabLocation(new FileSystemResource(keytabLocation));
	    loginConfig.setServicePrincipal(servicePrincipal);
	    loginConfig.setDebug(true);
	    loginConfig.setIsInitiator(true);
	    contextSource.setLoginConfig(loginConfig);
	    return contextSource;
	}

	@Bean
	public LdapUserDetailsService ldapUserDetailsService() {
	    FilterBasedLdapUserSearch userSearch =
	            new FilterBasedLdapUserSearch(ldapSearchBase, ldapSearchFilter, kerberosLdapContextSource());
	    LdapUserDetailsService service = new LdapUserDetailsService(userSearch);
	    service.setUserDetailsMapper(new LdapUserDetailsMapper());
	    return service;
	}*/
	
	/*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder.authenticationProvider(activeDirectoryLdapAuthenticationProvider()).userDetailsService(userDetailsService());
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(activeDirectoryLdapAuthenticationProvider()));
    }
    @Bean
    public AuthenticationProvider activeDirectoryLdapAuthenticationProvider() {
        ActiveDirectoryLdapAuthenticationProvider provider = new ActiveDirectoryLdapAuthenticationProvider(AD_DOMAIN, AD_URL);
        provider.setConvertSubErrorCodesToExceptions(true);
        provider.setUseAuthenticationRequestCredentials(true);
        
        return provider;
    }*/
}

