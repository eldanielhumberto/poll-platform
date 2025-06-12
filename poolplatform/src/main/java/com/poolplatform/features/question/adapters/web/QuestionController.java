package com.poolplatform.features.question.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.question.adapters.dto.QuestionCreateDTO;
import com.poolplatform.features.question.adapters.dto.QuestionUpdateDTO;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Question>> getAll() {
        List<Question> questions = questionService.get();
        return ResponseEntity.ok(questions);
    }

    @PostMapping()
    public ResponseEntity<Question> save(@RequestBody QuestionCreateDTO requestDTO, Authentication authentication) {
        Optional<Survey> survey = surveyService.get(requestDTO.getSurveyId());
        if (!survey.isPresent())
            throw new RequestException("The survey does not exist", HttpStatus.NOT_FOUND);

        Question newQuestion = new Question();
        newQuestion.setQuestionText(requestDTO.getQuestionText());
        newQuestion.setSurvey(survey.get());
        newQuestion.setAuthor((User) authentication.getCredentials());

        Question questionSaved = questionService.upsert(newQuestion);
        return ResponseEntity.ok(questionSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable String id, @RequestBody QuestionUpdateDTO requesDto) {
        Optional<Question> questionOptional = questionService.get(id);
        if (!questionOptional.isPresent())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        Question question = questionOptional.get();
        question.setQuestionText(requesDto.getQuestionText());

        Question questionSaved = questionService.upsert(question);
        return ResponseEntity.ok(questionSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Optional<Question> questionOptional = questionService.get(id);
        if (!questionOptional.isPresent())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        questionService.remove(questionOptional.get());
        return ResponseEntity.ok(Map.of("message", "Question deleted"));
    }

}
