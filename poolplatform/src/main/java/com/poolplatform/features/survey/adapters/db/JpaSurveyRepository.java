package com.poolplatform.features.survey.adapters.db;

import java.util.List;
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
}
