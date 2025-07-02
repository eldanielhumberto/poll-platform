package com.poolplatform.features.answer.domain.models;

import com.poolplatform.features.user.domain.models.UserSummary;

public class SimpleAnswer {
    private String id;
    private UserSummary user;
    private SimpleSurvey survey;
    private SimpleQuestion question;
    private SimpleOption option;

    public SimpleAnswer() {
    }

    public SimpleAnswer(String id, UserSummary user, SimpleSurvey survey, SimpleQuestion question,
            SimpleOption option) {
        this.id = id;
        this.user = user;
        this.survey = survey;
        this.question = question;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SimpleSurvey getSurvey() {
        return survey;
    }

    public void setSurvey(SimpleSurvey survey) {
        this.survey = survey;
    }

    public SimpleQuestion getQuestion() {
        return question;
    }

    public void setQuestion(SimpleQuestion question) {
        this.question = question;
    }

    public SimpleOption getOption() {
        return option;
    }

    public void setOption(SimpleOption option) {
        this.option = option;
    }

    public UserSummary getUser() {
        return user;
    }

    public void setUser(UserSummary user) {
        this.user = user;
    }

}
