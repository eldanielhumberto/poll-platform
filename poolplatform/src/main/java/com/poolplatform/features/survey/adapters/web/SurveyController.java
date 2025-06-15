package com.poolplatform.features.survey.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.survey.adapters.dto.SurveyRequestDTO;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.survey.domain.models.SurveySummary;
import com.poolplatform.features.user.domain.models.User;

import java.time.Instant;
import java.util.List;
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

    @GetMapping("/get")
    public ResponseEntity<?> getSurveys(@RequestParam(required = false) String id) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        if (id != null) {
            Optional<Survey> surveyOptional = surveyService.get(id);
            if (!surveyOptional.isPresent())
                throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

            responseDTO.setMessage("Get a survey");
            responseDTO.setData(new SurveySummary(surveyOptional.get()));

            return ResponseEntity.ok(responseDTO);
        }

        List<Survey> surveys = surveyService.get();

        responseDTO.setMessage("Get all surveys");
        responseDTO.setData(surveys.stream().map(SurveySummary::new).toList());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/get/me")
    public ResponseEntity<?> getUserSurveys(Authentication authentication) {
        List<Survey> surveys = surveyService.get((User) authentication.getCredentials());

        ResponseDTO<List<SurveySummary>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Your surveys");
        responseDTO.setData(surveys.stream().map(SurveySummary::new).toList());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> saveSurvey(@RequestBody SurveyRequestDTO rSurveyRequestDTO,
            Authentication authentication) {
        Survey newSurvey = new Survey();
        newSurvey.setTitle(rSurveyRequestDTO.getTitle());
        newSurvey.setDescription(rSurveyRequestDTO.getDescription());
        newSurvey.setAuthor((User) authentication.getCredentials());
        newSurvey.setCreatedAt(Instant.now());
        surveyService.upsert(newSurvey);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey saved");

        return ResponseEntity.ok(responseDTO);
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

        ResponseDTO<Survey> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey saved");
        responseDTO.setData(surveySaved);

        return ResponseEntity.ok(responseDTO);
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

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey deleted");

        return ResponseEntity.ok(responseDTO);
    }

}
