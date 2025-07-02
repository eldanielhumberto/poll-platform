package com.poolplatform.features.option.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.option.domain.OptionRepository;
import com.poolplatform.features.option.domain.OptionService;
import com.poolplatform.features.option.domain.models.Option;

@Service
public class OptionApplication implements OptionService {

    @Autowired
    private OptionRepository optionRepository;

    @Override
    public List<Option> get() {
        return optionRepository.get();
    }

    @Override
    public Optional<Option> get(String id) {
        return optionRepository.get(id);
    }

    @Override
    public void remove(Option t) {
        optionRepository.remove(t);
    }

    @Override
    public Option upsert(Option t) {
        return optionRepository.upsert(t);
    }

}
