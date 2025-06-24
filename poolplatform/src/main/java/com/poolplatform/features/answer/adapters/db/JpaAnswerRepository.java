package com.poolplatform.features.answer.adapters.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.adapters.mappers.AnswerMapper;
import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

@Repository
public interface JpaAnswerRepository extends JpaRepository<AnswerEntity, String>, AnswerRepository {

    @Override
    default List<Answer> get() {
        return findAll().stream().map(AnswerMapper::toAnswer).collect(Collectors.toList());
    }

    @Override
    default Answer get(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default List<Answer> get(Survey survey) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default List<Answer> get(Question question) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    default Answer upsert(Answer answer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'upsert'");
    }

}
