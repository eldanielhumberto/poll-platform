package com.poolplatform.features.question.adapters.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.adapters.mappers.UserMapper;

public class QuestionMapper {
    public static Question toQuestion(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestionText(questionEntity.getQuestionText());
        question.setSurvey(SurveyMapper.toSurvey(questionEntity.getSurvey()));
        question.setAuthor(UserMapper.toUser(questionEntity.getAuthor()));

        if (questionEntity.getOptions() != null) {
            question.setOptions(questionEntity.getOptions().stream().map(o -> {
                Survey surveyEntity = new Survey();
                surveyEntity.setId(o.getSurvey().getId());

                Question questionEntityForOption = new Question();
                questionEntityForOption.setId(o.getQuestion().getId());

                Option option = new Option();
                option.setId(o.getId());
                option.setOptionText(o.getOptionText());
                option.setSurvey(surveyEntity);
                option.setQuestion(questionEntityForOption);

                return option;
            }).collect(Collectors.toList()));
        }

        return question;
    }

    public static QuestionEntity toQuestionEntity(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(question.getId());
        questionEntity.setQuestionText(question.getQuestionText());
        questionEntity.setSurvey(SurveyMapper.toSurveyEntity(question.getSurvey()));
        questionEntity.setAuthor(UserMapper.toUserEntity(question.getAuthor()));

        if (question.getOptions() != null) {
            questionEntity.setOptions(question.getOptions().stream().map(o -> {
                SurveyEntity surveyEntity = new SurveyEntity();
                surveyEntity.setId(o.getSurvey().getId());

                QuestionEntity questionEntityForOption = new QuestionEntity();
                questionEntityForOption.setId(o.getQuestion().getId());

                OptionEntity option = new OptionEntity();
                option.setId(o.getId());
                option.setOptionText(o.getOptionText());
                option.setSurvey(surveyEntity);
                option.setQuestion(questionEntityForOption);

                return option;
            }).collect(Collectors.toList()));

        }

        return questionEntity;
    }

    public static List<QuestionEntity> toQuestionEntities(List<Question> questions) {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (Question question : questions) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setQuestionText(question.getQuestionText());
            questionEntity.setSurvey(SurveyMapper.toSurveyEntity(question.getSurvey()));
            questionEntity.setAuthor(UserMapper.toUserEntity(question.getAuthor()));

            List<OptionEntity> optionEntities = new ArrayList<>();
            for (Option option : question.getOptions()) {
                OptionEntity optionEntity = new OptionEntity();
                optionEntity.setOptionText(option.getOptionText());
                optionEntity.setSurvey(SurveyMapper.toSurveyEntity(option.getSurvey()));
                optionEntity.setQuestion(questionEntity);
                optionEntities.add(optionEntity);
            }
            questionEntity.setOptions(optionEntities);
            questionEntities.add(questionEntity);
        }

        return questionEntities;
    }
}
