package com.poolplatform.features.user.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.user.domain.UserRepository;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

@Service
public class UserApplication implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Optional<User> getById(String id) {
        return userRepository.getWithId(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
