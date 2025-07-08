package com.poolplatform.features.survey.domain.models;

import java.time.Instant;
import java.util.List;

import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.models.Visit;

public class Survey {
    private String id;
    private String title;
    private String description;
    private User author;
    private List<Question> questions;
    private List<Visit> visits;
    private List<Option> options;
    private List<Answer> answers;
    private Instant createdAt;

    public Survey() {
    }

    public Survey(String id, String title, String description, User author, List<Question> questions,
            List<Visit> visits,
            Instant createdAt, List<Option> options, List<Answer> answers) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.questions = questions;
        this.visits = visits;
        this.options = options;
        this.answers = answers;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
