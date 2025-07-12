package com.poolplatform.features.survey.domain.models;

import java.time.Instant;
import java.util.List;

import com.poolplatform.features.category.domain.models.CategoryResponse;
import com.poolplatform.features.question.domain.models.QuestionResponse;
import com.poolplatform.features.user.domain.models.SimpleUser;

// Represents a survey response for the client, containing survey details and questions
public class OneSurveyResponse {
    private String id;
    private String title;
    private String description;
    private SimpleUser author;
    private CategoryResponse category;
    private List<QuestionResponse> questions;
    private int answers;
    private Instant createdAt;

    public OneSurveyResponse() {
    }

    public OneSurveyResponse(String id, String title, String description, SimpleUser author, int answers,
            List<QuestionResponse> questions, Instant createdAt, CategoryResponse category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.answers = answers;
        this.questions = questions;
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

    public SimpleUser getAuthor() {
        return author;
    }

    public void setAuthor(SimpleUser author) {
        this.author = author;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public List<QuestionResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionResponse> questions) {
        this.questions = questions;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public CategoryResponse getCategory() {
        return category;
    }

    public void setCategory(CategoryResponse category) {
        this.category = category;
    }

}
