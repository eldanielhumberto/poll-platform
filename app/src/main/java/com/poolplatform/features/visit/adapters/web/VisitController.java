package com.poolplatform.features.visit.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.adapters.dto.CreateVisitDto;
import com.poolplatform.features.visit.adapters.mappers.VisitMapper;
import com.poolplatform.features.visit.domain.VisitService;
import com.poolplatform.features.visit.domain.models.SimpleVisit;
import com.poolplatform.features.visit.domain.models.Visit;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<?> getVisits(@RequestParam String surveyId, @RequestParam(required = false) String userId) {

        Optional<Survey> surveyOptional = surveyService.get(surveyId);
        if (surveyOptional.isEmpty())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        // Get visits if user is present in the params
        if (userId != null) {
            Optional<User> userOptional = userService.getById(userId);
            if (userOptional.isPresent()) {
                List<Visit> visits = visitService.get(userOptional.get(), surveyOptional.get());

                ResponseDTO<List<SimpleVisit>> responseDTO = new ResponseDTO<>();
                responseDTO.setMessage("Get survey visits");
                responseDTO.setData(visits.stream().map(VisitMapper::toSimpleVisit).collect(Collectors.toList()));

                return ResponseEntity.ok(responseDTO);
            }
        }

        // Get visits for surveys
        Survey survey = surveyOptional.get();
        List<Visit> visits = visitService.getSurveyVisits(survey);

        ResponseDTO<List<SimpleVisit>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Get survey visits");
        responseDTO.setData(visits.stream().map(VisitMapper::toSimpleVisit).collect(Collectors.toList()));

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody CreateVisitDto visitDto, Authentication authentication) {
        Optional<Survey> surveyOptional = surveyService.get(visitDto.getSurveyId());
        if (surveyOptional.isEmpty())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        Survey survey = surveyOptional.get();

        Visit visit = new Visit();
        visit.setSurvey(survey);
        visit.setVisited(survey.getAuthor());
        visit.setVisitedAt(Instant.now());
        if (authentication != null) {
            visit.setVisitor((User) authentication.getCredentials());
        }

        visitService.createVisit(visit);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Visit created");

        return ResponseEntity.ok(responseDTO);
    }

}
