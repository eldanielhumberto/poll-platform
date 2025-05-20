package com.poolplatform.features.auth.application;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.poolplatform.features.auth.domain.AuthService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthApplication implements AuthService {
    private String secret_key = "12345678901234567890123456789012";
    private Key key = new SecretKeySpec(secret_key.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String generateToken(String email) {
        Instant now = Instant.now();
        Instant expires = now.plus(2, ChronoUnit.HOURS);

        return Jwts
                .builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expires))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String encoderPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean matchPassword(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);
    }

}
