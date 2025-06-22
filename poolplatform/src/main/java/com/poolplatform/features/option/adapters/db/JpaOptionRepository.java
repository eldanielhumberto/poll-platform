package com.poolplatform.features.option.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.OptionRepository;
import com.poolplatform.features.option.domain.models.Option;

@Repository
public interface JpaOptionRepository extends JpaRepository<OptionEntity, String>, OptionRepository {

    @Override
    default List<Option> get() {
        return findAll().stream().map(OptionMapper::toOption).collect(Collectors.toList());
    }

    @Override
    default Optional<Option> get(String id) {
        return findById(id).map(OptionMapper::toOption);
    }

    @Override
    default void remove(Option t) {
        delete(OptionMapper.toOptionEntity(t));
    }

    @Override
    default Option upsert(Option t) {
        return OptionMapper.toOption(save(OptionMapper.toOptionEntity(t)));
    }

}
