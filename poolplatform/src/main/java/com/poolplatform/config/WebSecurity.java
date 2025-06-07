package com.poolplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.poolplatform.features.auth.application.AuthApplication;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.filters.JwtAuthorizationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurity {
        @Autowired
        private UserService userService;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                httpSecurity.csrf(csrf -> csrf.disable())
                                .sessionManagement(
                                                sessionManagement -> sessionManagement
                                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(http -> http
                                                .requestMatchers("/api/auth/signup", "/api/auth/login").permitAll()
                                                .anyRequest().authenticated()) // This is bad
                                .addFilterAfter(new JwtAuthorizationFilter(new AuthApplication(),
                                                userService),
                                                UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
        }
}
