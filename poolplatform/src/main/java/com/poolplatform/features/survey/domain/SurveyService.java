package com.poolplatform.features.survey.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public interface SurveyService {
    List<Survey> get();

    List<Survey> get(User user);

    Optional<Survey> get(String id);

    Survey save(Survey survey);

    void delete(Survey survey);
}
