package com.poolplatform.features.visit.domain.models;

import com.poolplatform.features.survey.domain.models.Survey;

public class SimpleSurvey {
    private String id;
    private String title;

    public SimpleSurvey(Survey survey) {
        this.id = survey.getId();
        this.title = survey.getTitle();
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
