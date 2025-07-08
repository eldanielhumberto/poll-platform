package com.poolplatform.features.option.domain.models;

import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

public class Option {
    private String id;
    private String optionText;
    private Question question;
    private Survey survey;

    public Option() {
    }

    public Option(String id, String optionText, Question question, Survey survey) {
        this.id = id;
        this.optionText = optionText;
        this.question = question;
        this.survey = survey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

}
