package com.poolplatform.features.user.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import java.util.List;

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

        ResponseDTO<List<User>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("All users list");
        responseDTO.setData(users);

        return ResponseEntity.ok(responseDTO);
    }

}
