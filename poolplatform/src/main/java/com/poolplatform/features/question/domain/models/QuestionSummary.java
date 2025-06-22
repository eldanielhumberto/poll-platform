package com.poolplatform.features.question.domain.models;

public class QuestionSummary {
    private String id;
    private String questionText;
    private SimpleSurvey survey;

    public QuestionSummary(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();

        SimpleSurvey survey = new SimpleSurvey();
        survey.setId(question.getSurvey().getId());
        survey.setTitle(question.getSurvey().getTitle());

        this.survey = survey;
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
}
