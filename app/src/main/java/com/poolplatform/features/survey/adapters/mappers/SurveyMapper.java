package com.poolplatform.features.survey.adapters.mappers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.poolplatform.features.answer.adapters.mappers.AnswerMapper;
import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.domain.models.OneSurveyResponse;
import com.poolplatform.features.survey.domain.models.SimpleSurvey;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.survey.domain.models.SurveyResponse;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.visit.adapters.mappers.VisitMapper;
import com.poolplatform.features.category.adapters.mappers.CategoryMapper;

public class SurveyMapper {
    public static Survey toSurvey(SurveyEntity surveyEntity) {
        Map<String, Option> optionEntityMap = new HashMap<>(); // Map to avoid duplicates

        Survey survey = new Survey();
        survey.setId(surveyEntity.getId());
        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setAuthor(UserMapper.toUser(surveyEntity.getAuthor()));
        survey.setCategory(CategoryMapper.toBasicCategory(surveyEntity.getCategory()));
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        // Set visits
        if (surveyEntity.getVisits() != null) {
            survey.setVisits(
                    surveyEntity.getVisits().stream().map(VisitMapper::toSimpleVisitFromEntity)
                            .collect(Collectors.toList()));
        }

        // Set answers
        if (surveyEntity.getAnswers() != null) {
            survey.setAnswers(surveyEntity.getAnswers().stream().map(AnswerMapper::toSimpleAnswer)
                    .collect(Collectors.toList()));
        }

        // Set questions
        if (surveyEntity.getQuestions() != null) {
            survey.setQuestions(surveyEntity.getQuestions().stream()
                    .map(q -> QuestionMapper.mapQuestionsFromEntity(q, optionEntityMap)).toList());
        }

        // Set options
        if (surveyEntity.getOptions() != null) {
            survey.setOptions(surveyEntity.getOptions().stream()
                    .map(o -> OptionMapper.mapOptionFromEntity(o, optionEntityMap)).collect(Collectors.toList()));
        }

        return survey;
    }

    public static SurveyEntity toSurveyEntity(Survey survey) {
        Map<String, OptionEntity> optionEntityMap = new HashMap<>();

        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId(survey.getId());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setAuthor(UserMapper.toUserEntity(survey.getAuthor()));
        surveyEntity.setCategory(CategoryMapper.toBasicCategory(survey.getCategory()));
        surveyEntity.setCreatedAt(survey.getCreatedAt());

        // Set visits
        if (survey.getVisits() != null) {
            surveyEntity.setVisits(
                    survey.getVisits().stream().map(VisitMapper::toSimpleVisitEntity).collect(Collectors.toList()));
        }

        // Set answers
        if (survey.getAnswers() != null) {
            surveyEntity.setAnswers(
                    survey.getAnswers().stream().map(AnswerMapper::toSimpleAnswer).collect(Collectors.toList()));
        }

        // Set questions
        if (survey.getQuestions() != null) {
            surveyEntity.setQuestions(
                    survey.getQuestions().stream().map(q -> QuestionMapper.mapQuestion(q, optionEntityMap)).toList());
        }

        // Set options
        if (survey.getOptions() != null) {
            surveyEntity.setOptions(survey.getOptions().stream().map(o -> OptionMapper.mapOption(o, optionEntityMap))
                    .collect(Collectors.toList()));
        }

        return surveyEntity;
    }

    // Converts a Survey object to a SimpleSurvey object
    public static SimpleSurvey toSimpleSurvey(Survey survey) {
        SimpleSurvey simpleSurvey = new SimpleSurvey();
        simpleSurvey.setId(survey.getId());
        simpleSurvey.setTitle(survey.getTitle());

        return simpleSurvey;
    }

    // Converts a SurveyEntity object to a SimpleSurvey object
    public static Survey toBasicSurvey(SurveyEntity surveyEntity) {
        Survey simpleSurvey = new Survey();
        simpleSurvey.setId(surveyEntity.getId());
        simpleSurvey.setTitle(surveyEntity.getTitle());

        return simpleSurvey;
    }

    public static SurveyEntity toBasicSurvey(Survey survey) {
        SurveyEntity simpleSurveyEntity = new SurveyEntity();
        simpleSurveyEntity.setId(survey.getId());
        simpleSurveyEntity.setTitle(survey.getTitle());

        return simpleSurveyEntity;
    }

    // Converts a Survey object to a SurveyResponse object
    public static SurveyResponse toSurveyResponse(Survey survey) {
        SurveyResponse surveyResponse = new SurveyResponse();
        surveyResponse.setId(survey.getId());
        surveyResponse.setTitle(survey.getTitle());
        surveyResponse.setDescription(survey.getDescription());
        surveyResponse.setAuthor(UserMapper.toSimpleUser(survey.getAuthor()));
        surveyResponse.setCategory(CategoryMapper.toCategoryResponse(survey.getCategory()));
        surveyResponse.setVisits(survey.getVisits().size());
        surveyResponse.setAnswers(survey.getAnswers().size());
        surveyResponse.setQuestions(survey.getQuestions().size());
        surveyResponse.setCreatedAt(survey.getCreatedAt());

        return surveyResponse;
    }

    // Converts a Survey object to a OneSurveyResponse object
    public static OneSurveyResponse tOneSurveyResponse(Survey survey) {
        OneSurveyResponse oneSurveyResponse = new OneSurveyResponse();
        oneSurveyResponse.setId(survey.getId());
        oneSurveyResponse.setTitle(survey.getTitle());
        oneSurveyResponse.setDescription(survey.getDescription());
        oneSurveyResponse.setAuthor(UserMapper.toSimpleUser(survey.getAuthor()));
        oneSurveyResponse.setCategory(CategoryMapper.toCategoryResponse(survey.getCategory()));
        oneSurveyResponse.setAnswers(survey.getAnswers().size());
        oneSurveyResponse.setQuestions(survey.getQuestions().stream()
                .map(QuestionMapper::toQuestionResponse).collect(Collectors.toList()));

        oneSurveyResponse.setCreatedAt(survey.getCreatedAt());

        return oneSurveyResponse;
    }

}
