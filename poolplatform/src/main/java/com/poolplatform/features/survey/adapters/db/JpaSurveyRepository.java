package com.poolplatform.features.survey.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.SurveyRepository;
import com.poolplatform.features.survey.domain.models.Survey;

@Repository
public interface JpaSurveyRepository extends JpaRepository<SurveyEntity, String>, SurveyRepository {
    @Override
    default List<Survey> get() {
        return findAll().stream().map(SurveyMapper::toSurvey).collect(Collectors.toList());
    }

    @Override
    default Optional<Survey> get(String id) {
        return findById(id).map(SurveyMapper::toSurvey);
    }

    @Override
    default Survey save(Survey survey) {
        return SurveyMapper.toSurvey(save(SurveyMapper.toSurveyEntity(survey)));
    }

    @Override
    default void delete(Survey survey) {
        delete(SurveyMapper.toSurveyEntity(survey));
    }
}
