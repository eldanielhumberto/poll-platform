package com.poolplatform.features.visit.domain;

import java.util.List;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.visit.domain.models.Visit;

public interface VisitRepository {
    List<Visit> getSurveyVisits(Survey survey);

    Visit createVisit(Visit visit);
}
