package com.poolplatform.features.question.domain.models;

import com.poolplatform.features.survey.domain.models.SimpleSurvey;

// Represents a question response that includes a survey
public class QuestionResponseWithSurvey extends QuestionResponse {
    private SimpleSurvey survey;

    public QuestionResponseWithSurvey() {
    }

    public QuestionResponseWithSurvey(QuestionResponse questionResponse, SimpleSurvey survey) {
        super();

        this.survey = survey;
    }

    public SimpleSurvey getSurvey() {
        return survey;
    }

    public void setSurvey(SimpleSurvey survey) {
        this.survey = survey;
    }

}
