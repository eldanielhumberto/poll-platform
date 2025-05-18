package com.poolplatform.features.auth.adapters.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.features.auth.adapters.dto.SignupDTO;
import com.poolplatform.features.auth.domain.AuthService;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO signupDTO) {
        // Save user
        User newUser = new User();
        newUser.setUsername(signupDTO.getUsername());
        newUser.setEmail(signupDTO.getEmail());
        newUser.setPassword(authService.encoderPassword(signupDTO.getPassword()));

        User user = userService.save(newUser);

        // Get token
        String token = authService.generateToken(user.getEmail());

        // Set response
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login() { // TODO: Implement login function
        return ResponseEntity.ok("Login");
    }

}
