package com.poolplatform.features.category.domain.models;

public class CategoryResponse {
    private String id;
    private String name;
    private String color;
    private int surveysCount;

    public CategoryResponse() {
    }

    public CategoryResponse(String id, String name, String color, int surveysCount) {
        this.id = id;
        this.name = name;
        this.surveysCount = surveysCount;
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

    public int getSurveysCount() {
        return surveysCount;
    }

    public void setSurveysCount(int surveysCount) {
        this.surveysCount = surveysCount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
