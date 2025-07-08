package com.poolplatform.features.option.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.option.adapters.dto.CreateOptionDto;
import com.poolplatform.features.option.adapters.dto.UpdateOptionDto;
import com.poolplatform.features.option.domain.OptionService;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;

import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/api/options")
public class OptionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private OptionService optionService;

    @PostMapping()
    public ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CreateOptionDto optionDto,
            Authentication authentication) {
        Optional<Question> questionOptional = questionService.get(optionDto.getQuestionId());
        if (questionOptional.isEmpty())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        if (!questionOptional.get().getAuthor().getId().equals((authentication.getPrincipal())))
            throw new RequestException("No friend", HttpStatus.UNAUTHORIZED);

        Question question = questionOptional.get();

        Option option = new Option();
        option.setOptionText(optionDto.getOptionText());
        option.setSurvey(question.getSurvey());
        option.setQuestion(question);

        optionService.upsert(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option saved");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> update(@PathVariable String id, @Valid @RequestBody UpdateOptionDto optionDto,
            Authentication authentication) {

        Optional<Option> optionOptional = optionService.get(id);
        if (optionOptional.isEmpty())
            throw new RequestException("The option does not exist", HttpStatus.NOT_FOUND);

        if (!optionOptional.get().getQuestion().getAuthor().getId().equals((authentication.getPrincipal())))
            throw new RequestException("No friend", HttpStatus.UNAUTHORIZED);

        Option option = optionOptional.get();
        option.setOptionText(optionDto.getOptionText());

        optionService.upsert(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option saved");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> delete(@PathVariable String id, Authentication authentication) {
        Optional<Option> optionOptional = optionService.get(id);
        if (optionOptional.isEmpty())
            throw new RequestException("The option does not exist", HttpStatus.NOT_FOUND);

        if (!optionOptional.get().getQuestion().getAuthor().getId().equals(authentication.getPrincipal()))
            throw new RequestException("No friend", HttpStatus.UNAUTHORIZED);

        Option option = optionOptional.get();
        optionService.remove(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option removed");

        return ResponseEntity.ok(responseDTO);
    }

}
