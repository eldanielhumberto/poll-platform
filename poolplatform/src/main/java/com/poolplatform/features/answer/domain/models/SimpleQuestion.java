package com.poolplatform.features.answer.domain.models;

public class SimpleQuestion {
    private String id;
    private String questionText;

    public SimpleQuestion() {
    }

    public SimpleQuestion(String id, String questionText) {
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
