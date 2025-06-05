package com.poolplatform.features.survey.adapters.mappers;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.adapters.mappers.UserMapper;

public class SurveyMapper {
    public static Survey toSurvey(SurveyEntity surveyEntity) {
        Survey survey = new Survey();
        survey.setId(surveyEntity.getId());
        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setAuthor(UserMapper.toUser(surveyEntity.getAuthor()));
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        return survey;
    }
}
