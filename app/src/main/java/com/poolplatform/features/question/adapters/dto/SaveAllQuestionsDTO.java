package com.poolplatform.features.question.adapters.dto;

import java.util.List;

public class SaveAllQuestionsDTO {
    private List<QuestionForSaveAllDTO> questions;
    private String surveyId;

    public List<QuestionForSaveAllDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionForSaveAllDTO> questions) {
        this.questions = questions;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

}
