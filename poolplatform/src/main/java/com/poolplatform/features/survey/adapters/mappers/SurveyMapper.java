package com.poolplatform.features.survey.adapters.mappers;

import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
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
        survey.setQuestions(surveyEntity.getQuestions().stream().map(q -> {
            Question question = new Question();
            question.setId(q.getId());
            question.setQuestionText(q.getQuestionText());

            return question;
        }).toList());
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        return survey;
    }

    public static SurveyEntity toSurveyEntity(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId(survey.getId());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setAuthor(UserMapper.toUserEntity(survey.getAuthor()));
        surveyEntity.setQuestions(survey.getQuestions().stream().map(q -> {
            QuestionEntity question = new QuestionEntity();
            question.setId(q.getId());
            question.setQuestionText(q.getQuestionText());

            return question;
        }).toList());

        surveyEntity.setCreatedAt(survey.getCreatedAt());

        return surveyEntity;
    }
}
