package com.example.boot11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		String[] whiteList= {"/","/user/loginform","/user/login_fail","/user/expired", 
				"/user/signup_form", "/user/signup", "/error","/upload/images/*","/file/list/*"};
		
		httpSecurity
		.csrf(csrf->csrf.disable())
		.authorizeHttpRequests(config ->
			config
				.requestMatchers(whiteList).permitAll()
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
				.anyRequest().authenticated()
		)
		.formLogin(config->
			config
				.loginPage("/user/required_loginform")
				.loginProcessingUrl("/user/login")
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler())
				.failureForwardUrl("/user/login_fail")
				.permitAll()
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")
				.logoutSuccessUrl("/")
				.permitAll()
			
		).exceptionHandling(config->
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config->
			config
				.maximumSessions(1)
				.expiredUrl("/user/expired") 
		);
		return httpSecurity.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
