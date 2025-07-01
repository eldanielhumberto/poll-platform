package com.poolplatform.features.option.adapters.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateOptionDto {
    @NotNull
    @NotBlank
    private String optionText;

    @NotNull
    @NotBlank
    private String questionId;

    public CreateOptionDto() {
    }

    public CreateOptionDto(String optionText, String questionId) {
        this.optionText = optionText;
        this.questionId = questionId;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
