package com.poolplatform.features.visit.adapters.mappers;

import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.SimpleSurvey;
import com.poolplatform.features.visit.domain.models.SimpleVisit;
import com.poolplatform.features.visit.domain.models.Visit;

public class VisitMapper {
    public static Visit toVisit(VisitEntity visitEntity) {
        Visit visit = new Visit();
        visit.setId(visitEntity.getId());
        visit.setSurvey(SurveyMapper.toSurvey(visitEntity.getSurvey()));
        visit.setVisited(UserMapper.toUser(visitEntity.getVisited()));
        visit.setVisitedAt(visitEntity.getVisitedAt());
        if (visitEntity.getVisitor() != null) {
            visit.setVisitor(UserMapper.toUser(visitEntity.getVisitor()));
        }

        return visit;
    }

    public static VisitEntity toVisitEntity(Visit visit) {
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visit.getId());
        visitEntity.setSurvey(SurveyMapper.toSurveyEntity(visit.getSurvey()));
        visitEntity.setVisited(UserMapper.toUserEntity(visit.getVisited()));
        visitEntity.setVisitedAt(visit.getVisitedAt());

        if (visit.getVisitor() != null) {
            visitEntity.setVisitor(UserMapper.toUserEntity(visit.getVisitor()));
        }

        return visitEntity;
    }

    public static SimpleVisit toSimpleVisit(Visit visit) {
        SimpleVisit simpleVisit = new SimpleVisit();
        simpleVisit.setSurvey(new SimpleSurvey(visit.getSurvey()));
        simpleVisit.setVisited(visit.getVisited());
        simpleVisit.setVisitedAt(visit.getVisitedAt());

        if (visit.getVisitor() != null) {
            simpleVisit.setVisitor(visit.getVisitor());
        }

        return simpleVisit;
    }
}
