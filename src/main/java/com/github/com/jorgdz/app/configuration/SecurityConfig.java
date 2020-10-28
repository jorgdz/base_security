package com.github.com.jorgdz.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.github.com.jorgdz.app.service.security.SecurityService;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
   	private SecurityService serviceSecurity;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		this.serviceSecurity.loadAccessControllList(http)
			.loadFormLogin(http)
			.loadLogout(http)
			.loadAccessDenied(http);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		this.serviceSecurity.loadAuthDetailService(auth);
	}
}
