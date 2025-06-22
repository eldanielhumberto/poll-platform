package com.poolplatform.features.question.adapters.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.question.adapters.dto.QuestionCreateDTO;
import com.poolplatform.features.question.adapters.dto.QuestionUpdateDTO;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.question.domain.models.QuestionSummary;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/get")
    public ResponseEntity<?> getQuestions(@RequestParam(required = false) String id,
            @RequestParam(required = false) String surveyId) {
        ResponseDTO<Object> responseDTO = new ResponseDTO<>();

        if (id != null) {
            Optional<Question> questionOptional = questionService.get(id);
            if (!questionOptional.isPresent())
                throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

            responseDTO.setMessage("Get a question");
            responseDTO.setData(new QuestionSummary(questionOptional.get()));
            return ResponseEntity.ok(responseDTO);
        }

        if (surveyId != null) {
            Optional<Survey> survey = surveyService.get(surveyId);
            if (!survey.isPresent())
                throw new RequestException("The survey does not exist", HttpStatus.NOT_FOUND);

            List<Question> questions = questionService.get(survey.get());

            responseDTO.setMessage("Get questions by survey");
            responseDTO.setData(questions.stream().map(QuestionSummary::new).collect(Collectors.toList()));
            return ResponseEntity.ok(responseDTO);
        }

        List<Question> questions = questionService.get();

        responseDTO.setMessage("Get all questions");
        responseDTO.setData(questions.stream().map(QuestionSummary::new).collect(Collectors.toList()));
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody QuestionCreateDTO requestDTO, Authentication authentication) {
        Optional<Survey> survey = surveyService.get(requestDTO.getSurveyId());
        if (!survey.isPresent())
            throw new RequestException("The survey does not exist", HttpStatus.NOT_FOUND);

        if (!survey.get().getAuthor().getId().equals(authentication.getPrincipal()))
            throw new RequestException("Unauthorized", HttpStatus.UNAUTHORIZED);

        Optional<Question> question = questionService.getByText(requestDTO.getQuestionText(), requestDTO.getSurveyId());
        if (question.isPresent())
            throw new RequestException("The question already exists", HttpStatus.BAD_REQUEST);

        Question newQuestion = new Question();
        newQuestion.setQuestionText(requestDTO.getQuestionText());
        newQuestion.setSurvey(survey.get());
        newQuestion.setAuthor((User) authentication.getCredentials());
        questionService.upsert(newQuestion);

        ResponseDTO<Question> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Question saved");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody QuestionUpdateDTO requesDto,
            Authentication authentication) {
        Optional<Question> questionOptional = questionService.get(id);
        if (!questionOptional.isPresent())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        Question question = questionOptional.get();

        if (!question.getAuthor().getId().equals(authentication.getPrincipal()))
            throw new RequestException("Unauthorized", HttpStatus.UNAUTHORIZED);

        question.setQuestionText(requesDto.getQuestionText());
        questionService.upsert(question);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey saved");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id, Authentication authentication) {
        Optional<Question> questionOptional = questionService.get(id);
        if (!questionOptional.isPresent())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        if (!questionOptional.get().getAuthor().getId().equals(authentication.getPrincipal()))
            throw new RequestException("Unauthorized", HttpStatus.UNAUTHORIZED);

        questionService.remove(questionOptional.get());

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Survey deleted");

        return ResponseEntity.ok(responseDTO);
    }

}
