package com.poolplatform.features.survey.adapters.mappers;

import java.util.stream.Collectors;

import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.survey.domain.models.SurveySummary;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.models.UserSummary;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.Visit;

public class SurveyMapper {
    public static Survey toSurvey(SurveyEntity surveyEntity) {
        Survey survey = new Survey();
        survey.setId(surveyEntity.getId());
        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setAuthor(UserMapper.toUser(surveyEntity.getAuthor()));
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        if (surveyEntity.getQuestions() != null) {
            survey.setQuestions(surveyEntity.getQuestions().stream().map(q -> {
                Question question = new Question();
                question.setId(q.getId());
                question.setQuestionText(q.getQuestionText());

                return question;
            }).toList());
        }

        if (surveyEntity.getVisits() != null) {
            survey.setVisits(surveyEntity.getVisits().stream().map(v -> {
                Visit visit = new Visit();
                visit.setId(v.getId());
                return visit;
            }).collect(Collectors.toList()));
        }

        return survey;
    }

    public static SurveyEntity toSurveyEntity(Survey survey) {
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId(survey.getId());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setAuthor(UserMapper.toUserEntity(survey.getAuthor()));
        surveyEntity.setCreatedAt(survey.getCreatedAt());

        if (survey.getQuestions() != null) {
            surveyEntity.setQuestions(survey.getQuestions().stream().map(q -> {
                QuestionEntity question = new QuestionEntity();
                question.setId(q.getId());
                question.setQuestionText(q.getQuestionText());

                return question;
            }).toList());
        }

        if (survey.getVisits() != null) {
            surveyEntity.setVisits(survey.getVisits().stream().map(v -> {
                VisitEntity visit = new VisitEntity();
                visit.setId(v.getId());
                return visit;
            }).collect(Collectors.toList()));
        }

        return surveyEntity;
    }

    public static SurveySummary toSurveySummary(Survey survey) {
        SurveySummary surveySummary = new SurveySummary();
        surveySummary.setId(survey.getId());
        surveySummary.setTitle(survey.getTitle());
        surveySummary.setDescription(survey.getDescription());
        surveySummary.setAuthor(new UserSummary(survey.getAuthor()));
        surveySummary.setVisits(survey.getVisits().size());
        surveySummary.setCreatedAt(survey.getCreatedAt());

        return surveySummary;
    }
}
