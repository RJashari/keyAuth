package com.bpbbank;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigNew extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*auth.jdbcAuthentication()
		.usersByUsernameQuery("select username, password, enabled from users where username=?");
		auth
		.authenticationProvider(kerberosAuthenticationProvider());
//		.ldapAuthentication()
//		.contextSource(kerberosLdapContextSource());
////		.and()
//		auth.userDetailsService(ldapUserDetailsService());
//			.userDnPatterns("CN={0},OU=Sektori i Databazes dhe zhvillimit,OU=Departamenti i IT,OU=HQ Manage Users,OU=Managed Users")
//				.passwordEncoder(new LdapShaPasswordEncoder())
//				.passwordAttribute("userPassword");
//				.passwordEncoder(new PlaintextPasswordEncoder())
//				.passwordAttribute("mailNickname");
*/	}
	
	@Bean(name="passwordEncoder")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private String adServer = "ldap://192.178.10.10";//:389/dc=bpb,dc=com";

//	@Value("${app.service-principal}")
	private String servicePrincipal = "HTTP/it-02-00.bpb.com@BPB.COM";

//	@Value("${app.keytab-location}")
//	private String keytabLocation = "file:src/main/resources/keytab/gagi_.keytab";
	private String keytabLocation = "src/main/resources/keytab/gagi_.keytab";
//	private String keytabLocation = "C:\\Users\\ngadhnjim.berani\\workspace\\KeyAuthentication\\src\\main\\resources\\keytab";

//	@Value("${app.ldap-search-base}")
	private String ldapSearchBase = "dc=bpb,dc=com";

//	@Value("${app.ldap-search-filter}")
	private String ldapSearchFilter = "(|(userPrincipalName={0})(sAMAccountName={0})(mailNickname={0}))";
	
	/*@Bean
	public KerberosLdapContextSource kerberosLdapContextSource() {
		System.out.println("SYSOUT: " + new Date());
	    KerberosLdapContextSource contextSource = new KerberosLdapContextSource(adServer);
	    SunJaasKrb5LoginConfig loginConfig = new SunJaasKrb5LoginConfig();
	    loginConfig.setKeyTabLocation(new FileSystemResource(keytabLocation));
//	    loginConfig
	    loginConfig.setServicePrincipal(servicePrincipal);
	    loginConfig.setDebug(true);
	    loginConfig.setIsInitiator(true);
	    try {
			loginConfig.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    contextSource.setLoginConfig(loginConfig);
	    contextSource.afterPropertiesSet();
	    return contextSource;
	}*/

	/*@Bean
    public KerberosAuthenticationProvider kerberosAuthenticationProvider() {
        KerberosAuthenticationProvider provider =
        		new KerberosAuthenticationProvider();
        SunJaasKerberosClient client = new SunJaasKerberosClient();
        client.setDebug(true);
        provider.setKerberosClient(client);
        provider.setUserDetailsService(ldapUserDetailsService());
        return provider;
    }*/
	
/*	@Bean
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

