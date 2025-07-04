package com.poolplatform.features.visit.adapters.db;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.adapters.mappers.VisitMapper;
import com.poolplatform.features.visit.domain.VisitRepository;
import com.poolplatform.features.visit.domain.models.Visit;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;

@Repository
public interface JpaVisitRepository extends JpaRepository<VisitEntity, String>, VisitRepository {
    @Override
    default List<Visit> getSurveyVisits(Survey survey) {
        return findBySurvey(SurveyMapper.toSurveyEntity(survey)).stream().map(VisitMapper::toVisit)
                .collect(Collectors.toList());
    }

    @Override
    default Visit createVisit(Visit visit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createVisit'");
    }

    List<VisitEntity> findBySurvey(SurveyEntity survey);
}
