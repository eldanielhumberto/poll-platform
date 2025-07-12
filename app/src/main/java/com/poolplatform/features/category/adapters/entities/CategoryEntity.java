package com.poolplatform.features.category.adapters.entities;

import java.util.List;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "category")
    private List<SurveyEntity> surveys;

    public CategoryEntity() {
    }

    public CategoryEntity(String id, String name, String color, List<SurveyEntity> surveys) {
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

    public List<SurveyEntity> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<SurveyEntity> surveys) {
        this.surveys = surveys;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
