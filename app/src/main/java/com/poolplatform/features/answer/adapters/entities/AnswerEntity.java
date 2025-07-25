package com.poolplatform.features.answer.adapters.entities;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.user.adapters.entities.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne()
    @JoinColumn(name = "respondent_id", nullable = false)
    private UserEntity respondent;

    @ManyToOne()
    @JoinColumn(name = "survey_id", nullable = false)
    private SurveyEntity survey;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private OptionEntity option;

    public AnswerEntity() {
    }

    public AnswerEntity(String id, UserEntity user, UserEntity respondent, SurveyEntity survey, QuestionEntity question,
            OptionEntity option) {
        this.id = id;
        this.user = user;
        this.respondent = respondent;
        this.survey = survey;
        this.question = question;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public UserEntity getRespondent() {
        return respondent;
    }

    public void setRespondent(UserEntity respondent) {
        this.respondent = respondent;
    }

    public SurveyEntity getSurvey() {
        return survey;
    }

    public void setSurvey(SurveyEntity survey) {
        this.survey = survey;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public OptionEntity getOption() {
        return option;
    }

    public void setOption(OptionEntity option) {
        this.option = option;
    }

}
