package com.poolplatform.features.question.domain.models;

public class QuestionSummary {
    private String id;
    private String questionText;

    public QuestionSummary(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
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
