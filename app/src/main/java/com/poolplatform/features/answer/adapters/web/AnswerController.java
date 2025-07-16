package com.poolplatform.features.answer.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.answer.adapters.dto.CreateAnswerDto;
import com.poolplatform.features.answer.adapters.dto.SubmitAnswersDto;
import com.poolplatform.features.answer.adapters.mappers.AnswerMapper;
import com.poolplatform.features.answer.domain.AnswerService;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.answer.domain.models.AnswerResponse;
import com.poolplatform.features.option.domain.OptionService;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

import jakarta.validation.Valid;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private OptionService optionService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String param) {
        List<Answer> answers = answerService.get();

        ResponseDTO<List<AnswerResponse>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("All answers in the app");
        responseDTO.setData(answers.stream().map(AnswerMapper::toResponseAnswer).collect(Collectors.toList()));

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CreateAnswerDto createAnswerDto,
            Authentication authentication) {
        Optional<Option> option = optionService.get(createAnswerDto.getOptionId());
        if (option.isEmpty())
            throw new RequestException("The option does not exist", HttpStatus.BAD_REQUEST);

        Optional<Question> question = questionService.get(option.get().getQuestion().getId());
        if (question.isEmpty())
            throw new RequestException("The question does not exist", HttpStatus.BAD_REQUEST);

        Optional<Survey> survey = surveyService.get(question.get().getSurvey().getId());
        if (survey.isEmpty())
            throw new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST);

        Answer answer = new Answer();
        answer.setQuestion(question.get());
        answer.setOption(option.get());
        answer.setSurvey(survey.get());
        answer.setUser((User) authentication.getCredentials());

        Answer answerSaved = answerService.upsert(answer);

        ResponseDTO<AnswerResponse> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Answer saved!");
        responseDTO.setData(AnswerMapper.toResponseAnswer(answerSaved));

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/submit-survey")
    public ResponseEntity<?> submit(@RequestBody SubmitAnswersDto dto, Authentication authentication) {
        List<Answer> answersToSave = new ArrayList<>();
        for (String optionId : dto.getOptions()) {
            Option option = optionService.get(optionId)
                    .orElseThrow(() -> new RequestException("The option does not exist", HttpStatus.BAD_REQUEST));

            Question question = questionService.get(option.getQuestion().getId())
                    .orElseThrow(() -> new RequestException("The question does not exist", HttpStatus.BAD_REQUEST));

            Survey survey = surveyService.get(question.getSurvey().getId())
                    .orElseThrow(() -> new RequestException("The survey does not exist", HttpStatus.BAD_REQUEST));

            Answer newAnswer = new Answer();
            newAnswer.setQuestion(question);
            newAnswer.setOption(option);
            newAnswer.setSurvey(survey);
            newAnswer.setUser(survey.getAuthor());

            answersToSave.add(newAnswer);
        }

        answerService.upsertAll(answersToSave);

        ResponseDTO<AnswerResponse> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Answers saved!");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        Optional<Answer> answer = answerService.get(id);
        if (answer.isEmpty())
            throw new RequestException("The answer does not exist", HttpStatus.BAD_REQUEST);

        answerService.remove(answer.get());

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Answer deleted!");

        return ResponseEntity.ok(responseDTO);
    }

}
