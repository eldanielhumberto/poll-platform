package com.poolplatform.features.category.domain.models;

import java.util.List;

import com.poolplatform.features.survey.domain.models.Survey;

public class Category {
    private String id;
    private String name;
    private String color;
    private List<Survey> surveys;

    public Category() {
    }

    public Category(String id, String name, String color, List<Survey> surveys) {
        this.id = id;
        this.name = name;
        this.surveys = surveys;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
