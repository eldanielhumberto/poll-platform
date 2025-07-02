package com.poolplatform.features.answer.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.adapters.mappers.AnswerMapper;
import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

@Repository
public interface JpaAnswerRepository extends JpaRepository<AnswerEntity, String>, AnswerRepository {

    @Override
    default List<Answer> get() {
        return findAll().stream().map(AnswerMapper::toAnswer).collect(Collectors.toList());
    }

    @Override
    default Optional<Answer> get(String id) {
        return findById(id).map(AnswerMapper::toAnswer);
    }

    @Override
    default List<Answer> get(Survey survey, User user) {
        return findBySurveyAndUser(survey.getId(), user.getId()).stream().map(AnswerMapper::toAnswer)
                .collect(Collectors.toList());
    }

    @Override
    default List<Answer> get(Question question) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default Answer upsert(Answer answer) {
        return AnswerMapper.toAnswer(save(AnswerMapper.toAnswerEntity(answer)));
    }

    @Override
    default void remove(Answer answer) {
        delete(AnswerMapper.toAnswerEntity(answer));
    }

    @NativeQuery("select * from answers where survey_id = ?1 AND user_id = ?2")
    List<AnswerEntity> findBySurveyAndUser(String surveyId, String userId);
}
