package com.poolplatform.features.auth.adapters.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.auth.adapters.dto.SigninDTO;
import com.poolplatform.features.auth.adapters.dto.SignupDTO;
import com.poolplatform.features.auth.domain.AuthService;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDTO signupDTO) {
        // Find user for verify if exists
        Optional<User> userOptional = userService.getByEmail(signupDTO.getEmail());
        if (userOptional.isPresent())
            throw new RequestException("The account already exists", HttpStatus.BAD_REQUEST);

        // Save user
        User newUser = new User();
        newUser.setUsername(signupDTO.getUsername());
        newUser.setEmail(signupDTO.getEmail());
        newUser.setPassword(authService.encoderPassword(signupDTO.getPassword()));

        User user = userService.save(newUser);

        // Get token
        String token = authService.generateToken(user.getId());

        // Set response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SigninDTO signinDTO) {
        // Verify email
        Optional<User> userOptional = userService.getByEmail(signinDTO.getEmail());
        if (!userOptional.isPresent())
            throw new RequestException("Incorrect credentials", HttpStatus.BAD_REQUEST);

        // Verify password
        String encodedPassword = userOptional.get().getPassword();
        if (!authService.matchPassword(signinDTO.getPassword(), encodedPassword))
            throw new RequestException("Incorrect credentials", HttpStatus.BAD_REQUEST);

        // Set data
        User user = userOptional.get();
        String token = authService.generateToken(user.getId());

        // Set response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(Authentication authentication) {
        return ResponseEntity.ok(authentication.getCredentials());
    }

}
