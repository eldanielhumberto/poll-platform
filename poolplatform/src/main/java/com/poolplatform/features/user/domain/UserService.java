package com.poolplatform.features.user.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.user.domain.models.User;

public interface UserService {
    List<User> getAll();

    Optional<User> getById(String id);

    Optional<User> getByEmail(String email);

    User save(User user);
}
