package com.poolplatform.features.visit.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.visit.domain.VisitRepository;
import com.poolplatform.features.visit.domain.VisitService;
import com.poolplatform.features.visit.domain.models.Visit;

@Service
public class VisitApplication implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public List<Visit> getSurveyVisits(Survey survey) {
        return visitRepository.getSurveyVisits(survey);
    }

    @Override
    public Visit createVisit(Visit visit) {
        return visitRepository.createVisit(visit);
    }
}
