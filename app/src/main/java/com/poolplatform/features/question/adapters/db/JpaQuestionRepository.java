package com.poolplatform.features.question.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.question.domain.QuestionRepository;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

import jakarta.transaction.Transactional;

@Repository
public interface JpaQuestionRepository extends JpaRepository<QuestionEntity, String>, QuestionRepository {
    @Override
    default List<Question> get() {
        return findAll().stream().map(QuestionMapper::toQuestion).collect(Collectors.toList());
    }

    @Override
    default List<Question> get(Survey survey) {
        return getBySurvey(survey.getId()).stream().map(QuestionMapper::toQuestion).collect(Collectors.toList());
    }

    @Override
    default Optional<Question> get(String id) {
        return findById(id).map(QuestionMapper::toQuestion);
    }

    @Override
    default Optional<Question> getByText(String questionText, String surveyId) {
        return findByQuestionTextAndSurveyId(questionText, surveyId).map(QuestionMapper::toQuestion);
    }

    @Override
    default Question upsert(Question t) {
        return QuestionMapper.toQuestion(save(QuestionMapper.toQuestionEntity(t)));
    }

    @Override
    default void saveAll(List<Question> questions) {
        saveAll(QuestionMapper.toQuestionEntities(questions));
    }

    @Override
    default void remove(Question t) {
        delete(QuestionMapper.toQuestionEntity(t));
    }

    @Override
    default void deleteQuestionsBySurveyId(Survey survey) {
        deleteQuestionsBySurveyId(survey.getId());
    }

    @NativeQuery(value = "SELECT * FROM questions WHERE survey_id = ?1")
    List<QuestionEntity> getBySurvey(String surveyId);

    @NativeQuery(value = "SELECT * FROM questions WHERE question_text = ?1 AND survey_id = ?2")
    Optional<QuestionEntity> findByQuestionTextAndSurveyId(String questionText, String surveyId);

    @Modifying
    @Transactional
    @Query(value = "delete from questions where survey_id = ?1", nativeQuery = true)
    void deleteQuestionsBySurveyId(String surveyId);

}
