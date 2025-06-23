package com.poolplatform.features.survey.domain.models;

import java.time.Instant;

import com.poolplatform.features.user.domain.models.UserSummary;

public class SurveySummary {
    private String id;
    private String title;
    private String description;
    private UserSummary author;
    private Instant createdAt;

    public SurveySummary(Survey survey) {
        this.id = survey.getId();
        this.title = survey.getTitle();
        this.description = survey.getDescription();
        this.author = new UserSummary(survey.getAuthor());
        this.createdAt = survey.getCreatedAt();
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

    public UserSummary getAuthor() {
        return author;
    }

    public void setAuthor(UserSummary author) {
        this.author = author;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
