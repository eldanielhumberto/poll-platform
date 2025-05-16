package com.poolplatform.features.user.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(Map.of("users", users));
    }

}
