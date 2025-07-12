package com.poolplatform.features.survey.adapters.entities;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.category.adapters.entities.CategoryEntity;
import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;

import jakarta.persistence.CascadeType;
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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<QuestionEntity> questions;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitEntity> visits;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OptionEntity> options;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AnswerEntity> answers;

    @CreatedDate
    private Instant createdAt;

    public SurveyEntity() {
    }

    public SurveyEntity(String id, String title, String description, UserEntity author, List<QuestionEntity> questions,
            List<VisitEntity> visits,
            Instant createdAt, List<OptionEntity> options, List<AnswerEntity> answers, CategoryEntity category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.questions = questions;
        this.visits = visits;
        this.options = options;
        this.answers = answers;
        this.createdAt = createdAt;
        this.category = category;
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

    public List<VisitEntity> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitEntity> visits) {
        this.visits = visits;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

}
