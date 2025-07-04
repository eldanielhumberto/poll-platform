package com.poolplatform.features.visit.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.visit.domain.VisitService;
import com.poolplatform.features.visit.domain.models.Visit;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/visits")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @Autowired
    private SurveyService surveyService;

    @GetMapping()
    public ResponseEntity<?> getVisits(@RequestParam String surveyId) {
        Optional<Survey> surveyOptional = surveyService.get(surveyId);
        if (surveyOptional.isEmpty())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        Survey survey = surveyOptional.get();
        List<Visit> visits = visitService.getSurveyVisits(survey);

        ResponseDTO<List<Visit>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Get survey visits");
        responseDTO.setData(visits);

        return ResponseEntity.ok(responseDTO);
    }

}
