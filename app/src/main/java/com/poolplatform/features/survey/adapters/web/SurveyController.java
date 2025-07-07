package com.poolplatform.features.survey.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.answer.domain.AnswerService;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.survey.adapters.dto.ResetSurveyDto;
import com.poolplatform.features.survey.adapters.dto.SurveyRequestDTO;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.survey.domain.models.SurveySummary;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.VisitService;
import com.poolplatform.features.visit.domain.models.Visit;

import jakarta.validation.Valid;

import java.time.Instant;
import java.util.ArrayList;
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

    @Autowired
    private AnswerService answerService;

    @Autowired
    private VisitService visitService;

    @GetMapping("/get")
    public ResponseEntity<?> getSurveys(@RequestParam(required = false) String id, Authentication authentication) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        // Search survey with "id"
        if (id != null) {
            Optional<Survey> surveyOptional = surveyService.get(id);
            if (surveyOptional.isEmpty())
                throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

            Survey survey = surveyOptional.get();

            // Create a visit
            if (authentication != null && !authentication.getPrincipal().equals(survey.getAuthor().getId())) {
                User user = (User) authentication.getCredentials();

                Visit visit = new Visit();
                visit.setSurvey(survey);
                visit.setVisited(survey.getAuthor());
                visit.setVisitor(user);
                visit.setVisitedAt(Instant.now());

                visitService.createVisit(visit);
                System.out.println(authentication.getCredentials());
            }

            responseDTO.setMessage("Get a survey");
            responseDTO.setData(SurveyMapper.toSurveySummary(survey));

            return ResponseEntity.ok(responseDTO);
        }

        List<Survey> surveys = surveyService.get();

        responseDTO.setMessage("Get all surveys");
        responseDTO.setData(surveys.stream().map(SurveyMapper::toSurveySummary).toList());

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/get/me")
    public ResponseEntity<?> getUserSurveys(Authentication authentication) {
        List<Survey> surveys = surveyService.get((User) authentication.getCredentials());

        ResponseDTO<List<SurveySummary>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Your surveys");
        responseDTO.setData(surveys.stream().map(SurveyMapper::toSurveySummary).toList());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> saveSurvey(@Valid @RequestBody SurveyRequestDTO rSurveyRequestDTO,
            Authentication authentication) {
        Survey newSurvey = new Survey();
        newSurvey.setTitle(rSurveyRequestDTO.getTitle());
        newSurvey.setDescription(rSurveyRequestDTO.getDescription());
        newSurvey.setAuthor((User) authentication.getCredentials());
        newSurvey.setQuestions(new ArrayList<>());
        newSurvey.setCreatedAt(Instant.now());
        surveyService.upsert(newSurvey);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey saved");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSurvey(@PathVariable String id,
            @Valid @RequestBody SurveyRequestDTO rSurveyRequestDTO,
            Authentication authentication) {
        Optional<Survey> surveyOptional = surveyService.get(id);
        if (surveyOptional.isEmpty())
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
        if (surveyOptional.isEmpty())
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

    @PostMapping("/reset")
    public ResponseEntity<?> reset(@Valid @RequestBody ResetSurveyDto resetSurveyDto, Authentication authentication) {
        Optional<Survey> survey = surveyService.get(resetSurveyDto.getSurveyId());
        if (survey.isEmpty())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        List<Answer> answers = answerService.get(survey.get(), (User) authentication.getCredentials());
        if (answers.size() > 0) {
            surveyService.reset(survey.get(), (User) authentication.getCredentials());
        }

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey restarted");

        return ResponseEntity.ok(responseDTO);
    }

}
