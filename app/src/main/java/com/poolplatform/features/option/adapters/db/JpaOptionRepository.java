package com.poolplatform.features.option.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.OptionRepository;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

import jakarta.transaction.Transactional;

@Repository
public interface JpaOptionRepository extends JpaRepository<OptionEntity, String>, OptionRepository {

    @Override
    default List<Option> get() {
        return findAll().stream().map(OptionMapper::toOption).collect(Collectors.toList());
    }

    @Override
    default Optional<Option> get(String id) {
        return findById(id).map(OptionMapper::toOption);
    }

    @Override
    default Optional<Option> getByText(String optionText, Question question) {
        return findByOptionTextAndSurveyId(optionText, question.getId())
                .map(OptionMapper::toOption);
    }

    @Override
    default void remove(Option t) {
        delete(OptionMapper.toOptionEntity(t));
    }

    @Override
    default Option upsert(Option t) {
        return OptionMapper.toOption(save(OptionMapper.toOptionEntity(t)));
    }

    @Override
    default void deleteOptionsBySurveyId(Survey survey) {
        deleteOptionsBySurveyId(survey.getId());
    }

    @Query(value = "SELECT * FROM options WHERE option_text = ?1 AND question_id = ?2", nativeQuery = true)
    Optional<OptionEntity> findByOptionTextAndSurveyId(String optionText, String questionId);

    @Transactional
    @Modifying
    @Query(value = "delete from options where survey_id = ?1", nativeQuery = true)
    void deleteOptionsBySurveyId(String surveyId);
}
