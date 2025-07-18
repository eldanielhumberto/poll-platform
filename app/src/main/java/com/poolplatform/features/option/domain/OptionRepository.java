package com.poolplatform.features.option.domain;

import java.util.Optional;

import com.poolplatform.domain.interfaces.Crud;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

public interface OptionRepository extends Crud<Option> {
    Optional<Option> getByText(String optionText, Question question);

    void deleteOptionsBySurveyId(Survey survey);
}
