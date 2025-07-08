package com.poolplatform.features.visit.domain;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.models.Visit;

public interface VisitService {
    List<Visit> get(User visitor, Survey survey);

    List<Visit> getSurveyVisits(Survey survey);

    Visit createVisit(Visit visit);

    void registerVisitIfAllowed(Authentication authentication, Survey survey);
}
