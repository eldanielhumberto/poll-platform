package com.poolplatform.features.visit.domain;

import java.util.List;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.models.Visit;

public interface VisitRepository {
    List<Visit> get(User visitor, Survey survey);

    List<Visit> getSurveyVisits(Survey survey);

    Visit createVisit(Visit visit);

    void deleteVisitsBySurveyId(Survey survey);
}
