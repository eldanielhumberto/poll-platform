package com.poolplatform.features.survey.domain.models;

import java.time.Instant;
import java.util.List;

import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.user.domain.models.User;

public class Survey {
    private String id;
    private String title;
    private String description;
    private User author;
    private List<Question> questions;
    private Instant createdAt;

    public Survey() {
    }

    public Survey(String id, String title, String description, User author, List<Question> questions,
            Instant createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.questions = questions;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
