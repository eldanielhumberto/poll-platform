package com.poolplatform.features.survey.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.survey.adapters.dto.SurveyRequestDTO;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "/api/surveys")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @GetMapping()
    public ResponseEntity<?> getUserSurveys(Authentication authentication) {
        List<Survey> surveys = surveyService.get((User) authentication.getCredentials());
        return ResponseEntity.ok(Map.of("surveys", surveys));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getSurveys() {
        List<Survey> surveys = surveyService.get();
        return ResponseEntity.ok(Map.of("surveys", surveys));
    }

    @PostMapping()
    public ResponseEntity<?> saveSurvey(@RequestBody SurveyRequestDTO rSurveyRequestDTO,
            Authentication authentication) {
        Survey newSurvey = new Survey();
        newSurvey.setTitle(rSurveyRequestDTO.getTitle());
        newSurvey.setDescription(rSurveyRequestDTO.getDescription());
        newSurvey.setAuthor((User) authentication.getCredentials());
        newSurvey.setCreatedAt(Instant.now());

        Survey surveySaved = surveyService.upsert(newSurvey);
        return ResponseEntity.ok(surveySaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSurvey(@PathVariable String id, @RequestBody SurveyRequestDTO rSurveyRequestDTO,
            Authentication authentication) {
        Optional<Survey> surveyOptional = surveyService.get(id);
        if (!surveyOptional.isPresent())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        Survey survey = surveyOptional.get();
        String authorId = survey.getAuthor().getId();
        if (!authorId.equals(authentication.getPrincipal().toString()))
            throw new RequestException("No friend", HttpStatus.UNAUTHORIZED);

        survey.setTitle(rSurveyRequestDTO.getTitle());
        survey.setDescription(rSurveyRequestDTO.getDescription());

        Survey surveySaved = surveyService.upsert(survey);
        return ResponseEntity.ok(Map.of("Previous survey", survey, "Current survey", surveySaved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSurvey(@PathVariable String id, Authentication authentication) {
        Optional<Survey> surveyOptional = surveyService.get(id);
        if (!surveyOptional.isPresent())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        Survey survey = surveyOptional.get();
        String authorId = survey.getAuthor().getId();
        if (!authorId.equals(authentication.getPrincipal().toString()))
            throw new RequestException("No friend", HttpStatus.UNAUTHORIZED);

        surveyService.remove(survey);
        return ResponseEntity.ok(Map.of("message", "Deleted " + id));
    }

}
