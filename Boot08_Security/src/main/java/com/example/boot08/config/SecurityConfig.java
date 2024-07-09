package com.example.boot08.config;

import org.springframework.beans.factory.aot.AutowiredArgumentsCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //설정 클래스라고 알려준다
@EnableWebSecurity //Security 를 설정하기 위한 어노테이션
public class SecurityConfig {
	/*
	 *  매개변수에 전달되는 HttpSecurity 객체를 이용해서 우리의 프로젝트 상황에 맞는 설정을 기반으로 
	 *  만들어진 SecurityFilterChain 객체를 리턴해주어야 한다.
	 *  또한 SecurityFilterChain 객체도 스프링이 관리하는 Bean 이 되어야 한다  
	 */
	@Bean //메소드에서 리턴되는 SecurityFilterChain 을 bean 으로 만들어준다.
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		String[] whiteList= {"/","/play","/user/loginform","/user/login_fail","/user/expired"};
		
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
				//인증을 거치지 않은 사용자를 리다일렉트 시킬 경로 설정
				.loginPage("/user/required_loginform")
				//로그인 처리를 할 떄 요청될 url 설정 (spring security 가 login 처리를 대신해준다
				.loginProcessingUrl("/user/login")
				//로그인 처리를 대신 하러면 어던 파라미터명으로 userName 과 password 가 넘어오는지 알려주기
				.usernameParameter("userName")
				.passwordParameter("password")
				.successHandler(new AuthSuccessHandler()) //로그인 성공
				.failureForwardUrl("/user/login_fail")
				.permitAll()//위에 명시한 모든 요청 경로를 로그인 없이 요청할 수 있도록 설정
		)
		.logout(config ->
			config
				.logoutUrl("/user/logout")//Spring Security 가 자동으로 로그아웃 처리해줄 경로 설정
				.logoutSuccessUrl("/")//로그아웃 이후에 리다일렉트 시킬 경로 설정
				.permitAll()
			
		).exceptionHandling(config->
			//403 forbidden 인 경우 forward 이동 시킬 경로설정
			config.accessDeniedPage("/user/denied")
		)
		.sessionManagement(config->
			config
				.maximumSessions(1) //최대 허용 세션 갯수
				.expiredUrl("/user/expired") //허용 세선 갯수가 넘어서 로그인 해제된 경우 리다일렉트 이동시킬 경로
		);
		
		return httpSecurity.build();
	}
	
	//비밀번호를 암호화 해주는 객체를 bean 으로 만든다
	@Bean
	PasswordEncoder passwordEncoder() {
		//여기서 리턴해주는 객체도 bean 으로 된다
		return new BCryptPasswordEncoder();
	}
	
	//인증 매니저 객체를 bean 으로 만든다 (Spring Security 가 자동으로 로그인 처리할 떄도 사용되는 객체)
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity http,
			BCryptPasswordEncoder encoder, UserDetailsService service) throws Exception{
		//적절한 설정을 한 인증 매니저 객체를 리턴해주면 bean 이 되어서 Spring Security 가 사용한다
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(service)
				.passwordEncoder(encoder)
				.and()
				.build();
	}
}




