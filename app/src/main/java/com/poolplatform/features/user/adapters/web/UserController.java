package com.poolplatform.features.user.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.user.domain.models.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll() {
        List<User> users = userService.getAll();

        ResponseDTO<List<UserResponse>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("All users list");
        responseDTO.setData(users.stream().map(UserMapper::toUserResponse).collect(Collectors.toList()));

        return ResponseEntity.ok(responseDTO);
    }

}
