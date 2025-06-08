package com.poolplatform.features.survey.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.survey.domain.models.Survey;

public interface SurveyService {
    List<Survey> get();

    Optional<Survey> get(String id);

    Survey save(Survey survey);

    void delete(Survey survey);
}
