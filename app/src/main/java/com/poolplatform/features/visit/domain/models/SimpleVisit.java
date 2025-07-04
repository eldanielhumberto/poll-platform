package com.poolplatform.features.visit.domain.models;

import java.time.Instant;

public class SimpleVisit {
    private SimpleUser visitor;
    private SimpleUser visited;
    private SimpleSurvey survey;
    private Instant visitedAt;

    public SimpleUser getVisitor() {
        return visitor;
    }

    public void setVisitor(SimpleUser visitor) {
        this.visitor = visitor;
    }

    public SimpleUser getVisited() {
        return visited;
    }

    public void setVisited(SimpleUser visited) {
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
