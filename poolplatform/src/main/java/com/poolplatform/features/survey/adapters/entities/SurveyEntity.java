package com.poolplatform.features.survey.adapters.entities;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.user.adapters.entities.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "surveys")
public class SurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;

    @OneToMany(mappedBy = "survey")
    private List<QuestionEntity> questions;

    @CreatedDate
    private Instant createdAt;

    public SurveyEntity() {
    }

    public SurveyEntity(String id, String title, String description, UserEntity author, List<QuestionEntity> questions,
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

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

}
