package com.poolplatform.features.answer.adapters.mappers;

import java.util.List;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.answer.domain.models.AnswerResponse;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.models.SimpleOption;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.question.domain.models.SimpleQuestion;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.models.SimpleSurvey;
import com.poolplatform.features.user.adapters.mappers.UserMapper;

public class AnswerMapper {
    public static Answer toAnswer(AnswerEntity answerEntity) {
        Answer answer = new Answer();
        answer.setId(answerEntity.getId());
        answer.setOption(OptionMapper.toOption(answerEntity.getOption()));
        answer.setQuestion(QuestionMapper.toQuestion(answerEntity.getQuestion()));
        answer.setSurvey(SurveyMapper.toSurvey(answerEntity.getSurvey()));
        answer.setUser(UserMapper.toUser(answerEntity.getUser()));
        answer.setRespondent(UserMapper.toUser(answerEntity.getRespondent()));

        return answer;
    }

    public static AnswerEntity toAnswerEntity(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setId(answer.getId());
        answerEntity.setOption(OptionMapper.toOptionEntity(answer.getOption()));
        answerEntity.setQuestion(QuestionMapper.toQuestionEntity(answer.getQuestion()));
        answerEntity.setSurvey(SurveyMapper.toSurveyEntity(answer.getSurvey()));
        answerEntity.setUser(UserMapper.toUserEntity(answer.getUser()));
        answerEntity.setRespondent(UserMapper.toUserEntity(answer.getRespondent()));

        return answerEntity;
    }

    public static AnswerResponse toResponseAnswer(Answer answer) {
        SimpleSurvey simpleSurvey = new SimpleSurvey();
        simpleSurvey.setId(answer.getSurvey().getId());
        simpleSurvey.setTitle(answer.getSurvey().getTitle());

        SimpleQuestion simpleQuestion = new SimpleQuestion();
        simpleQuestion.setId(answer.getQuestion().getId());
        simpleQuestion.setQuestionText(answer.getQuestion().getQuestionText());

        SimpleOption simpleOption = new SimpleOption();
        simpleOption.setId(answer.getOption().getId());
        simpleOption.setOptionText(answer.getOption().getOptionText());

        AnswerResponse simpleAnswer = new AnswerResponse();
        simpleAnswer.setId(answer.getId());
        simpleAnswer.setOption(simpleOption);
        simpleAnswer.setQuestion(simpleQuestion);
        simpleAnswer.setSurvey(simpleSurvey);

        if (answer.getUser() != null && answer.getRespondent() != null) {
            simpleAnswer.setUser(UserMapper.toSimpleUser(answer.getUser()));
            simpleAnswer.setRespondent(UserMapper.toSimpleUser(answer.getRespondent()));
        }

        return simpleAnswer;
    }

    public static Answer toSimpleAnswer(AnswerEntity answer) {
        Answer simpleAnswer = new Answer();
        simpleAnswer.setId(answer.getId());
        simpleAnswer.setSurvey(SurveyMapper.toBasicSurvey(answer.getSurvey()));
        simpleAnswer.setOption(OptionMapper.toSimpleOption(answer.getOption()));
        simpleAnswer.setQuestion(QuestionMapper.toBasicQuestion(answer.getQuestion()));
        simpleAnswer.setUser(UserMapper.toBasicUser(answer.getUser()));

        return simpleAnswer;
    }

    public static AnswerEntity toSimpleAnswer(Answer answer) {
        AnswerEntity simpleAnswerEntity = new AnswerEntity();
        simpleAnswerEntity.setId(answer.getId());
        simpleAnswerEntity.setSurvey(SurveyMapper.toBasicSurvey(answer.getSurvey()));
        simpleAnswerEntity.setOption(OptionMapper.toSimpleOption(answer.getOption()));
        simpleAnswerEntity.setQuestion(QuestionMapper.toBasicQuestion(answer.getQuestion()));
        simpleAnswerEntity.setUser(UserMapper.toBasicUser(answer.getUser()));

        return simpleAnswerEntity;
    }

    public static List<AnswerEntity> toAnswersEntitiesList(List<Answer> answers) {
        return answers.stream().map(a -> toAnswerEntity(a)).toList();
    }
}
