package com.poolplatform.features.auth.domain;

public interface AuthService {
    String generateToken(String email);

    String encoderPassword(String password);

    boolean matchPassword(String password, String encodedPassword);
}
