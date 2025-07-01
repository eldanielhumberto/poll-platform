package com.poolplatform.features.survey.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.survey.domain.SurveyRepository;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

@Service
public class SurveyApplication implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public List<Survey> get() {
        return surveyRepository.get();
    }

    @Override
    public List<Survey> get(User user) {
        return surveyRepository.get(user);
    }

    @Override
    public Optional<Survey> get(String id) {
        return surveyRepository.get(id);
    }

    @Override
    public Survey upsert(Survey survey) {
        return surveyRepository.upsert(survey);
    }

    @Override
    public void remove(Survey survey) {
        surveyRepository.remove(survey);
    }

    @Override
    public void reset(Survey survey, User user) {
        surveyRepository.reset(survey, user);
    }
}
