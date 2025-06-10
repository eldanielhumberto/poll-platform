package com.poolplatform.adapters.filters;

import java.io.IOException;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.poolplatform.features.auth.domain.AuthService;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private AuthService authService;
    private UserService userService;

    public JwtAuthorizationFilter(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null && authService.validateToken(token)) {
                Claims credentials = authService.getClaims(token);

                Optional<User> user = userService.getById(credentials.getSubject());
                if (!user.isPresent())
                    throw new Error("User is not present");

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        credentials.getSubject(), user.get(), null);

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            System.out.println("Invalid AuthtorizationFilter: " + e);
        }

        filterChain.doFilter(request, response);
    }
}
