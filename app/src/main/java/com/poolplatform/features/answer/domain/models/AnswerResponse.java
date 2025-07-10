package com.poolplatform.features.answer.domain.models;

import com.poolplatform.features.option.domain.models.SimpleOption;
import com.poolplatform.features.question.domain.models.SimpleQuestion;
import com.poolplatform.features.survey.domain.models.SimpleSurvey;
import com.poolplatform.features.user.domain.models.SimpleUser;

public class AnswerResponse {
    private String id;
    private SimpleUser user;
    private SimpleSurvey survey;
    private SimpleQuestion question;
    private SimpleOption option;

    public AnswerResponse() {
    }

    public AnswerResponse(String id, SimpleUser user, SimpleSurvey survey, SimpleQuestion question,
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

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

}
