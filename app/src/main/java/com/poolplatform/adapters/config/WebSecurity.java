package com.poolplatform.adapters.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.poolplatform.adapters.filters.JwtAuthorizationFilter;
import com.poolplatform.features.auth.domain.AuthService;
import com.poolplatform.features.user.domain.UserService;

@EnableWebSecurity
@Configuration
public class WebSecurity {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(csrf -> csrf.disable())
				.sessionManagement(
						sessionManagement -> sessionManagement
								.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(http -> http
						.requestMatchers(
								"/api/auth/signup",
								"/api/auth/login",
								"/api/users/get",
								"/api/surveys/get",
								"/api/questions/get",
								"/api/answers/get",
								"/api/visits",
								"/swagger-ui/**",
								"/api-docs/**")
						.permitAll()
						.anyRequest().authenticated())
				.addFilterAfter(new JwtAuthorizationFilter(authService,
						userService),
						UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
}
