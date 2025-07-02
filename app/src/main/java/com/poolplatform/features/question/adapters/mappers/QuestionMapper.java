package com.poolplatform.features.question.adapters.mappers;

import java.util.stream.Collectors;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
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
                return new Option(o.getId(), o.getOptionText(), null);
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
                return new OptionEntity(o.getId(), o.getOptionText(), null);
            }).collect(Collectors.toList()));

        }

        return questionEntity;
    }
}
