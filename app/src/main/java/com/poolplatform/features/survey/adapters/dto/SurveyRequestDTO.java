package com.poolplatform.features.survey.adapters.dto;

import java.util.List;

import com.poolplatform.features.question.adapters.dto.QuestionForSaveAllDTO;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SurveyRequestDTO {
    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private List<QuestionForSaveAllDTO> questions;

    @NonNull
    @NotBlank
    private String categoryId;

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

    public List<QuestionForSaveAllDTO> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionForSaveAllDTO> questions) {
        this.questions = questions;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
