package com.ebs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
 * 29/11/2022
 * Admin acces is working fine
 * default pages also working fine
 *  NOTE-
 *  1- Register page not working
 *  2- User access is not working
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{ //extends WebSecurityConfigurerAdapter

	@Autowired
	private UserDetailsService userDetailsService;

	//Its a default Spring Security Interface uses to do default Authentication

	private static final String[] WHITE_LIST_URLS = {
			"/ebs/wc",
			"/ebs/home"
			
			
	};
	private static final String[] USER_LIST_URLS = {
			"/ebs/user",
			"/ebs/user/{userName}"

	};
	private static final String[] ADMIN_LIST_URLS = {
			"/ebs/**",
			"/ebs/register",  
			"/ebs/user",
			"/ebs/user/{userName}"
	};


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		//DAO= Data Access Object
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers(WHITE_LIST_URLS).permitAll()
		.antMatchers(USER_LIST_URLS).hasAuthority("user")
		.antMatchers(ADMIN_LIST_URLS).hasAuthority("admin")	
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}



}
