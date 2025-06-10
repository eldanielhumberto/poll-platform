package com.poolplatform.domain.interfaces;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public interface Crud<T> {
    List<T> get();

    List<T> get(User user);

    Optional<T> get(String id);

    Survey save(T t);

    void remove(T t);
}
