package com.poolplatform.features.user.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.user.domain.models.User;

public interface UserRepository {
    List<User> getAll();

    Optional<User> getByEmail(String email);

    User save(User user);
}
