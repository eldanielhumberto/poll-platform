package com.poolplatform.features.answer.adapters.mappers;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.user.adapters.mappers.UserMapper;

public class AnswerMapper {
    public static Answer toAnswer(AnswerEntity answerEntity) {
        Answer answer = new Answer();
        answer.setId(answerEntity.getId());
        answer.setUser(UserMapper.toUser(answerEntity.getUser()));
        answer.setOption(OptionMapper.toOption(answerEntity.getOption()));
        answer.setQuestion(QuestionMapper.toQuestion(answerEntity.getQuestion()));
        answer.setSurvey(SurveyMapper.toSurvey(answerEntity.getSurvey()));

        return answer;
    }

    public static AnswerEntity toAnswerEntity(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(answer.getId());
        answerEntity.setUser(UserMapper.toUserEntity(answer.getUser()));
        answerEntity.setOption(OptionMapper.toOptionEntity(answer.getOption()));
        answerEntity.setQuestion(QuestionMapper.toQuestionEntity(answer.getQuestion()));
        answerEntity.setSurvey(SurveyMapper.toSurveyEntity(answer.getSurvey()));

        return answerEntity;
    }
}
