package com.poolplatform.features.answer.adapters.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.adapters.mappers.AnswerMapper;
import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.models.User;

import jakarta.transaction.Transactional;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;

@Repository
public interface JpaAnswerRepository extends JpaRepository<AnswerEntity, String>, AnswerRepository {

    @Override
    default List<Answer> get(Survey survey, User respondent) {
        return findBySurveyAndRespondent(SurveyMapper.toSurveyEntity(survey), UserMapper.toUserEntity(respondent))
                .stream()
                .map(AnswerMapper::toAnswer)
                .toList();
    }

    @Override
    default List<Answer> get(Survey survey) {
        return findBySurvey(SurveyMapper.toSurveyEntity(survey)).stream().map(AnswerMapper::toAnswer).toList();
    }

    @Override
    default void upsertAll(List<Answer> answers) {
        saveAll(AnswerMapper.toAnswersEntitiesList(answers));
    }

    @Override
    default void remove(Answer answer) {
        delete(AnswerMapper.toAnswerEntity(answer));
    }

    @Override
    default void deleteAnswersBySurveyId(Survey survey) {
        deleteAnswersBySurveyId(survey.getId());
    }

    List<AnswerEntity> findBySurveyAndRespondent(SurveyEntity survey, UserEntity respondent);

    List<AnswerEntity> findBySurvey(SurveyEntity survey);

    @Modifying
    @Transactional
    @Query(value = "delete from answers where survey_id = ?1", nativeQuery = true)
    void deleteAnswersBySurveyId(String surveyId);
}
