package com.poolplatform.features.category.adapters.mappers;

import java.util.stream.Collectors;

import com.poolplatform.features.category.adapters.entities.CategoryEntity;
import com.poolplatform.features.category.domain.models.Category;
import com.poolplatform.features.category.domain.models.CategoryResponse;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;

public class CategoryMapper {
    public static Category toCategory(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setColor(entity.getColor());
        category.setName(entity.getName());

        if (entity.getSurveys() != null) {
            category.setSurveys(entity.getSurveys().stream()
                    .map(SurveyMapper::toBasicSurvey)
                    .collect(Collectors.toList()));
        }

        return category;
    }

    public static CategoryEntity toCategoryEntity(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setColor(category.getColor());
        entity.setName(category.getName());

        if (entity.getSurveys() != null) {
            entity.setSurveys(category.getSurveys().stream()
                    .map(SurveyMapper::toBasicSurveyEntity)
                    .collect(Collectors.toList()));
        }

        return entity;
    }

    /**
     * Converts a Category to a basic CategoryEntity.
     * 
     * @param category the Category to convert
     * @return a CategoryEntity containing only the id and name
     */
    public static CategoryEntity toBasicCategory(Category category) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(category.getId());
        entity.setColor(category.getColor());
        entity.setName(category.getName());

        return entity;
    }

    /**
     * Converts a CategoryEntity to a basic Category object.
     * 
     * @param entity the CategoryEntity to convert
     * @return a Category containing only the id and name
     */
    public static Category toBasicCategory(CategoryEntity entity) {
        Category category = new Category();
        category.setId(entity.getId());
        category.setColor(entity.getColor());
        category.setName(entity.getName());

        return category;
    }

    /**
     * Converts a Category to a CategoryResponse.
     * 
     * @param category the Category to convert
     * @return a CategoryResponse containing the name of the category
     */
    public static CategoryResponse toCategoryResponse(Category category) {
        CategoryResponse response = new CategoryResponse();
        response.setId(category.getId());
        response.setColor(category.getColor());
        response.setName(category.getName());
        response.setSurveysCount(category.getSurveys() != null ? category.getSurveys().size() : 0);

        return response;
    }

    /**
     * Converts a CategoryEntity to a CategoryResponse.
     * 
     * @param entity the CategoryEntity to convert
     * @return a CategoryResponse containing the name of the category
     */
    public static CategoryResponse toCategoryResponse(CategoryEntity entity) {
        CategoryResponse response = new CategoryResponse();
        response.setId(entity.getId());
        response.setColor(entity.getColor());
        response.setName(entity.getName());
        response.setSurveysCount(entity.getSurveys() != null ? entity.getSurveys().size() : 0);

        return response;
    }
}
