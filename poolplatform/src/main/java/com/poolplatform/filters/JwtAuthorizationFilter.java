package com.poolplatform.filters;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.poolplatform.features.auth.domain.AuthService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private AuthService authService;

    public JwtAuthorizationFilter(AuthService authService) {
        this.authService = authService;
    }

    // TODO: After implementing this, on login when the credentials are invalid, we
    // get 403

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && authService.validateToken(token)) {
                Claims credentials = authService.getClaims(token);

                // TODO: Get user from database and validate it
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        credentials.getSubject(), null, null); // TODO: study this

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println("Invalid AuthtorizationFilter: " + e);
        }

        filterChain.doFilter(request, response);
    }
}
