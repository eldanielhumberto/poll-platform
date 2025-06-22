package com.poolplatform.features.question.domain.models;

import java.util.List;

import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public class Question {
    private String id;
    private String questionText;
    private Survey survey;
    private User author;
    private List<Option> options;

    public Question() {
    }

    public Question(String id, String questionText, Survey survey, User author, List<Option> options) {
        this.id = id;
        this.questionText = questionText;
        this.survey = survey;
        this.author = author;
        this.options = options;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

}
