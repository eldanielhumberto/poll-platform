package com.poolplatform.features.option.domain.models;

import com.poolplatform.features.question.domain.models.Question;

public class Option {
    private String id;
    private String optionText;
    private Question question;

    public Option() {
    }

    public Option(String id, String optionText, Question question) {
        this.id = id;
        this.optionText = optionText;
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

}
