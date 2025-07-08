package com.poolplatform.features.survey.domain;

import java.util.List;

import com.poolplatform.domain.interfaces.Crud;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public interface SurveyService extends Crud<Survey> {
    List<Survey> get(User user);
}
