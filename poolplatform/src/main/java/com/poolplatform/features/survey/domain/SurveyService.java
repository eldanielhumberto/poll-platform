package com.poolplatform.features.survey.domain;

import java.util.List;

import com.poolplatform.features.survey.domain.models.Survey;

public interface SurveyService {
    List<Survey> getAll();

    Survey getById(String id);

    Survey save(Survey survey);

    Survey update(Survey survey);

    void delete(String id);
}
