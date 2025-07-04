package com.poolplatform.features.user.adapters.mappers;

import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.Visit;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        if (userEntity.getVisits() != null) {
            user.setVisits(userEntity.getVisits().stream().map(v -> {
                Visit visit = new Visit();
                visit.setId(v.getId());

                return visit;
            }).toList());
        }

        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        if (user.getVisits() != null) {
            userEntity.setVisits(user.getVisits().stream().map(v -> {
                VisitEntity visit = new VisitEntity();
                visit.setId(v.getId());

                return visit;
            }).toList());
        }

        return userEntity;
    }
}
