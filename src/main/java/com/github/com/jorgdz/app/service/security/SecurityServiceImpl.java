package com.github.com.jorgdz.app.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


/* 
 * 
 * Service Security configuration 
 */
@Component
public class SecurityServiceImpl implements SecurityService {

	@Autowired
   	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public SecurityService loadAccessControllList(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/index", "/css/**", "/js/**", "/images/**")
			.permitAll()
			.antMatchers("/create").hasAuthority("CREATE_USER")
			.antMatchers("/all").hasAuthority("SHOW_USERS")
			.antMatchers("/api/data").hasAuthority("API_DATOS")
			.anyRequest().authenticated();
		
		return this;
	}

	@Override
	public SecurityService loadFormLogin(HttpSecurity http) throws Exception {
		http.formLogin()
			.loginPage("/login")
			.permitAll();
		
		return this;
	}

	@Override
	public SecurityService loadLogout(HttpSecurity http) throws Exception {
		http.logout()
			.deleteCookies("JSESSIONID");
		
		return this;
	}

	@Override
	public SecurityService loadAccessDenied(HttpSecurity http) throws Exception {
		http.exceptionHandling()
			.accessDeniedPage("/error_403");
		
		return this;
	}

	@Override
	public SecurityService loadAuthDetailService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder);
	
		return this;
	}

}
