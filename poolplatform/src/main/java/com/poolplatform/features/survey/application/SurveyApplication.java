package com.poolplatform.features.survey.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.survey.domain.SurveyRepository;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;

@Service
public class SurveyApplication implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<Survey> get() {
        return surveyRepository.get();
    }
}
