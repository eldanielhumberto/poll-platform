package com.poolplatform.features.visit.domain.models;

import java.time.Instant;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public class Visit {
    private String id;
    private User visitor; // The user who visited
    private User visited; // The user who was visited
    private Survey survey;
    private Instant visitedAt; // The time when the visit occurred

    public Visit() {
    }

    public Visit(String id, Survey survey, User visitor, User visited) {
        this.id = id;
        this.survey = survey;
        this.visitor = visitor;
        this.visited = visited;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getVisitor() {
        return visitor;
    }

    public void setVisitor(User visitor) {
        this.visitor = visitor;
    }

    public User getVisited() {
        return visited;
    }

    public void setVisited(User visited) {
        this.visited = visited;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Instant getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Instant visitedAt) {
        this.visitedAt = visitedAt;
    }

}
