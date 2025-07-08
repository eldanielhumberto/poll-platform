package com.poolplatform.features.visit.application;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.VisitRepository;
import com.poolplatform.features.visit.domain.VisitService;
import com.poolplatform.features.visit.domain.models.Visit;

@Service
public class VisitApplication implements VisitService {
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public List<Visit> getSurveyVisits(Survey survey) {
        return visitRepository.getSurveyVisits(survey);
    }

    @Override
    public List<Visit> get(User visitor, Survey survey) {
        return visitRepository.get(visitor, survey);
    }

    @Override
    public Visit createVisit(Visit visit) {
        return visitRepository.createVisit(visit);
    }

    @Override
    public void registerVisitIfAllowed(Authentication authentication, Survey survey) {
        if (shouldRegisterVisit(authentication, survey)) {
            User user = (User) authentication.getCredentials();

            Visit visit = new Visit();
            visit.setSurvey(survey);
            visit.setVisited(survey.getAuthor());
            visit.setVisitor(user);
            visit.setVisitedAt(Instant.now());

            List<Visit> recentVisits = get(user, survey);

            if (recentVisits.isEmpty() || isVisitExpired(recentVisits.get(0))) {
                createVisit(visit);
            }
        }
    }

    // Check if one hour has passed since the last visit.
    public boolean isVisitExpired(Visit recentVisit) {
        return Instant.now().isAfter(recentVisit.getVisitedAt().plus(1, ChronoUnit.HOURS));
    }

    // Rule for saving a visit
    public boolean shouldRegisterVisit(Authentication authentication, Survey survey) {
        return authentication != null && !authentication.getPrincipal().equals(survey.getAuthor().getId());
    }
}
