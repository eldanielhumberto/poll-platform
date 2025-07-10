package com.poolplatform.features.survey.domain.models;

public class SimpleSurvey {
    private String id;
    private String title;

    public SimpleSurvey() {
    }

    public SimpleSurvey(Survey survey) {
        this.id = survey.getId();
        this.title = survey.getTitle();
    }

    public SimpleSurvey(String id, String title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
