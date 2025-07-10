package com.poolplatform.features.option.domain;

import java.util.Optional;

import com.poolplatform.domain.interfaces.Crud;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.models.Question;

public interface OptionRepository extends Crud<Option> {
    Optional<Option> getByText(String optionText, Question question);
}
