package com.poolplatform.features.survey.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(path = "/api/surveys")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping()
    public ResponseEntity<?> getSurveys() {
        List<Survey> surveys = surveyService.get();
        return ResponseEntity.ok(Map.of("surveys", surveys));
    }

}
