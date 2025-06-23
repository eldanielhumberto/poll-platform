package com.poolplatform.features.option.adapters.dto;

public class CreateOptionDto {
    private String optionText;
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
