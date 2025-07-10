package com.poolplatform.features.visit.adapters.mappers;

import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.models.SimpleSurvey;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.models.SimpleUser;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.VisitResponse;
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

    public static VisitResponse toVisitResponse(Visit visit) {
        VisitResponse visitResponse = new VisitResponse();
        visitResponse.setSurvey(new SimpleSurvey(visit.getSurvey()));
        visitResponse.setVisited(new SimpleUser(visit.getVisited()));
        visitResponse.setVisitedAt(visit.getVisitedAt());

        if (visit.getVisitor() != null) {
            visitResponse.setVisitor(new SimpleUser(visit.getVisitor()));
        }

        return visitResponse;
    }

    public static Visit toSimpleVisitFromEntity(VisitEntity visit) {
        Visit simpleVisit = new Visit();
        simpleVisit.setId(visit.getId());

        return simpleVisit;
    }

    public static VisitEntity toSimpleVisitEntity(Visit visit) {
        VisitEntity simpleVisitEntity = new VisitEntity();
        simpleVisitEntity.setId(visit.getId());

        return simpleVisitEntity;
    }
}
