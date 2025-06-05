package com.poolplatform.features.survey.domain;

import java.util.List;

import com.poolplatform.features.survey.domain.models.Survey;

public interface SurveyRepository {
    List<Survey> get();
}
