package com.poolplatform.features.visit.adapters.mappers;

import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.Visit;

public class VisitMapper {
    public static Visit toVisit(VisitEntity visitEntity) {
        Visit visit = new Visit();
        visit.setId(visitEntity.getId());
        visit.setSurvey(SurveyMapper.toSurvey(visitEntity.getSurvey()));
        visit.setVisited(UserMapper.toUser(visitEntity.getVisited()));
        visit.setVisitor(UserMapper.toUser(visitEntity.getVisitor()));
        visit.setVisitedAt(visitEntity.getVisitedAt());

        return visit;
    }

    public static VisitEntity toVisitEntity(Visit visit) {
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visit.getId());
        visitEntity.setSurvey(SurveyMapper.toSurveyEntity(visit.getSurvey()));
        visitEntity.setVisited(UserMapper.toUserEntity(visit.getVisited()));
        visitEntity.setVisitor(UserMapper.toUserEntity(visit.getVisitor()));
        visitEntity.setVisitedAt(visit.getVisitedAt());

        return visitEntity;
    }
}
