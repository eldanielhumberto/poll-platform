package com.poolplatform.features.question.domain.models;

import java.util.List;
import java.util.stream.Collectors;

import com.poolplatform.features.option.domain.models.SimpleOption;
import com.poolplatform.features.survey.domain.models.SimpleSurvey;

// Represents a question response for the client, containing question text and options
public class QuestionResponse {
    private String id;
    private String questionText;
    private List<SimpleOption> options;

    public QuestionResponse() {
    }

    public QuestionResponse(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();

        SimpleSurvey survey = new SimpleSurvey();
        survey.setId(question.getSurvey().getId());
        survey.setTitle(question.getSurvey().getTitle());

        this.options = question.getOptions().stream().map(o -> new SimpleOption(o.getId(), o.getOptionText()))
                .collect(Collectors.toList());
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

    public List<SimpleOption> getOptions() {
        return options;
    }

    public void setOptions(List<SimpleOption> options) {
        this.options = options;
    }

}
