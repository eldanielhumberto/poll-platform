package com.poolplatform.features.visit.adapters.entities;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.user.adapters.entities.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "visits")
public class VisitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private SurveyEntity survey;

    @ManyToOne
    @JoinColumn(name = "visitor_id", nullable = true)
    private UserEntity visitor;

    @ManyToOne
    @JoinColumn(name = "visited_id")
    private UserEntity visited;

    @CreatedDate
    private Instant visitedAt;

    public VisitEntity() {
    }

    public VisitEntity(String id, SurveyEntity survey, UserEntity visitor, UserEntity visited, Instant visitedAt) {
        this.id = id;
        this.survey = survey;
        this.visitor = visitor;
        this.visited = visited;
        this.visitedAt = visitedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getVisitor() {
        return visitor;
    }

    public void setVisitor(UserEntity visitor) {
        this.visitor = visitor;
    }

    public UserEntity getVisited() {
        return visited;
    }

    public void setVisited(UserEntity visited) {
        this.visited = visited;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public Instant getVisitedAt() {
        return visitedAt;
    }

    public void setVisitedAt(Instant visitedAt) {
        this.visitedAt = visitedAt;
    }

}
