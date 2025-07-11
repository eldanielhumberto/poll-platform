package com.poolplatform.features.option.adapters.entities;

import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "options")
public class OptionEntity {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "option_text", columnDefinition = "TEXT", nullable = false)
    private String optionText;

    @ManyToOne()
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne()
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    public OptionEntity() {
    }

    public OptionEntity(String id, String optionText, QuestionEntity question, SurveyEntity survey) {
        this.id = id;
        this.optionText = optionText;
        this.question = question;
        this.survey = survey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }
}
