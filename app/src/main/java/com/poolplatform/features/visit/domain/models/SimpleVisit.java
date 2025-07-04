package com.poolplatform.features.visit.domain.models;

import java.time.Instant;

import com.poolplatform.features.user.domain.models.User;

public class SimpleVisit {
    private User visitor;
    private User visited;
    private SimpleSurvey survey;
    private Instant visitedAt;

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

    public SimpleSurvey getSurvey() {
        return survey;
    }

    public void setSurvey(SimpleSurvey survey) {
        this.survey = survey;
    }

    public Instant getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Instant visitedAt) {
        this.visitedAt = visitedAt;
    }

}
