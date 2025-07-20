package com.poolplatform.features.answer.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
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
import com.poolplatform.features.user.domain.UserService;
import com.poolplatform.features.user.domain.models.User;

import java.util.ArrayList;
import java.util.List;
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
@RequestMapping(path = "/api/answers")
public class AnswerController {
	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private SurveyService surveyService;

	@Autowired
	private OptionService optionService;

	@GetMapping("/get")
	public ResponseEntity<?> get(@RequestParam() String surveyId,
			@RequestParam(required = false) String userId) {
		Survey survey = surveyService.get(surveyId)
				.orElseThrow(() -> new RequestException("The survey does not exist",
						HttpStatus.NOT_FOUND));

		if (userId != null) {
			User respondent = userService.getById(userId)
					.orElseThrow(() -> new RequestException("The user does not exist",
							HttpStatus.NOT_FOUND));

			List<Answer> answers = answerService.get(survey, respondent);
			return buildGetAnswersResponse(answers,
					String.format("Answers of '%s' in '%s'", respondent.getUsername(),
							survey.getTitle()));
		}

		List<Answer> answers = answerService.get(survey);
		return buildGetAnswersResponse(answers, String.format("Answers in '%s'", survey.getTitle()));
	}

	@PostMapping("/submit-survey")
	public ResponseEntity<?> submit(@RequestBody SubmitAnswersDto dto, Authentication authentication) {
		List<Answer> answersToSave = new ArrayList<>();
		for (String optionId : dto.getOptions()) {
			Option option = optionService.get(optionId)
					.orElseThrow(() -> new RequestException("The option does not exist",
							HttpStatus.NOT_FOUND));

			Question question = questionService.get(option.getQuestion().getId())
					.orElseThrow(() -> new RequestException("The question does not exist",
							HttpStatus.NOT_FOUND));

			Survey survey = surveyService.get(question.getSurvey().getId())
					.orElseThrow(() -> new RequestException("The survey does not exist",
							HttpStatus.NOT_FOUND));

			Answer newAnswer = new Answer();
			newAnswer.setQuestion(question);
			newAnswer.setOption(option);
			newAnswer.setSurvey(survey);
			newAnswer.setUser(survey.getAuthor());
			newAnswer.setRespondent((User) authentication.getCredentials());

			answersToSave.add(newAnswer);
		}

		answerService.upsertAll(answersToSave);

		ResponseDTO<AnswerResponse> responseDTO = new ResponseDTO<>();
		responseDTO.setMessage("Answers saved!");

		return ResponseEntity.ok(responseDTO);
	}

	private ResponseEntity<ResponseDTO<List<AnswerResponse>>> buildGetAnswersResponse(List<Answer> answers,
			String message) {
		ResponseDTO<List<AnswerResponse>> responseDTO = new ResponseDTO<>();
		responseDTO.setMessage(message);
		responseDTO.setData(answers.stream().map(AnswerMapper::toResponseAnswer).collect(Collectors.toList()));

		return ResponseEntity.ok(responseDTO);
	}
}
