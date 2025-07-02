package com.poolplatform.features.auth.domain;

import io.jsonwebtoken.Claims;

public interface AuthService {
    String generateToken(String email);

    boolean validateToken(String token);

    Claims getClaims(String token);

    String encoderPassword(String password);

    boolean matchPassword(String password, String encodedPassword);
}
