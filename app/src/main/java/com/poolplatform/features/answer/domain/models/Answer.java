package com.poolplatform.features.answer.domain.models;

import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public class Answer {
    private String id;
    private User user;
    private User respondent;
    private Option option;
    private Question question;
    private Survey survey;

    public Answer() {
    }

    public Answer(String id, User user, User respondent, Option option, Question question, Survey survey) {
        this.id = id;
        this.user = user;
        this.respondent = respondent;
        this.option = option;
        this.question = question;
        this.survey = survey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getRespondent() {
        return respondent;
    }

    public void setRespondent(User respondent) {
        this.respondent = respondent;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
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
