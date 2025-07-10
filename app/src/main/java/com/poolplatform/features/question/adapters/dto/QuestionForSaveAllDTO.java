package com.poolplatform.features.question.adapters.dto;

import java.util.List;

public class QuestionForSaveAllDTO {
    private String questionText;
    private List<String> options;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionsText) {
        this.questionText = questionsText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

}
