package com.poolplatform.domain.interfaces;

import java.util.List;
import java.util.Optional;

public interface Crud<M> {
    List<M> get();

    Optional<M> get(String id);

    M upsert(M t);

    void remove(M t);
}
