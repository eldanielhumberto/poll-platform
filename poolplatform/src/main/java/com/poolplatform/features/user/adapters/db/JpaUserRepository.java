package com.poolplatform.features.user.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.UserRepository;
import com.poolplatform.features.user.domain.models.User;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, String>, UserRepository {
    @Override
    default List<User> getAll() {
        return findAll().stream().map(UserMapper::toUser).collect(Collectors.toList());
    }

    @Override
    default User save(User user) {
        return UserMapper.toUser(save(UserMapper.toUserEntity(user)));
    }

    @Override
    default Optional<User> getByEmail(String email) {
        return getByEmailQuery(email).map(UserMapper::toUser);
    }

    @NativeQuery("SELECT * FROM users WHERE email = ?1")
    Optional<UserEntity> getByEmailQuery(String email);
}
