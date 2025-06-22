package com.poolplatform.features.option.adapters.db;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.domain.OptionRepository;
import com.poolplatform.features.option.domain.models.Option;

public interface JpaOptionRepository extends JpaRepository<OptionEntity, String>, OptionRepository {

    @Override
    default List<Option> get() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default Optional<Option> get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default void remove(Option t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    default Option upsert(Option t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upsert'");
    }

}
