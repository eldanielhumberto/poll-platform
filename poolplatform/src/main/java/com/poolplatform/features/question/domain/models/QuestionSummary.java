package com.poolplatform.features.question.domain.models;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionSummary {
    private String id;
    private String questionText;
    private SimpleSurvey survey;
    private List<SimpleOption> options;

    public QuestionSummary(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();

        SimpleSurvey survey = new SimpleSurvey();
        survey.setId(question.getSurvey().getId());
        survey.setTitle(question.getSurvey().getTitle());

        this.survey = survey;

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

    public SimpleSurvey getSurvey() {
        return survey;
    }

    public void setSurvey(SimpleSurvey survey) {
        this.survey = survey;
    }

    public List<SimpleOption> getOptions() {
        return options;
    }

    public void setOptions(List<SimpleOption> options) {
        this.options = options;
    }

}
