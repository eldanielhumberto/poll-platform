package com.poolplatform.features.question.domain.models;

public class SimpleOption {
    private String id;
    private String questionText;

    public SimpleOption() {
    }

    public SimpleOption(String id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }

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

}
