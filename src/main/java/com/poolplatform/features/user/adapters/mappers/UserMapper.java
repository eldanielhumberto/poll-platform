package com.poolplatform.features.user.adapters.mappers;

import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.domain.models.User;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        return userEntity;
    }
}
