package com.poolplatform.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.user.domain.models.User;

public interface Crud<M> {
    List<M> get();

    List<M> get(User user);

    Optional<M> get(String id);

    M upsert(M t);

    void remove(M t);
}
