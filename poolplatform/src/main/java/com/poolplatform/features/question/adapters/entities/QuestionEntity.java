package com.poolplatform.features.question.adapters.entities;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.user.adapters.entities.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "question_text", columnDefinition = "TEXT", nullable = false, unique = true)
    private String questionText;

    @ManyToOne()
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author;

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

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

}
