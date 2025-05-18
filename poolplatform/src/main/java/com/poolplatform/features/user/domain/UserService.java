package com.poolplatform.features.user.domain;

import java.util.List;

import com.poolplatform.features.user.domain.models.User;

public interface UserService {
    List<User> getAll();

    User save(User user);
}
