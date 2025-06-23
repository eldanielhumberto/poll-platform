package com.poolplatform.features.option.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.domain.exceptions.RequestException;
import com.poolplatform.features.option.adapters.dto.CreateOptionDto;
import com.poolplatform.features.option.domain.OptionService;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseDTO<?>> create(@RequestBody CreateOptionDto optionDto) {
        Optional<Question> questionOptional = questionService.get(optionDto.getQuestionId());
        if (questionOptional.isEmpty())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        Option option = new Option();
        option.setOptionText(optionDto.getOptionText());
        option.setQuestion(questionOptional.get());

        optionService.upsert(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option saved");

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> update(@PathVariable String id, @RequestBody CreateOptionDto optionDto) {
        Optional<Question> questionOptional = questionService.get(optionDto.getQuestionId());
        if (questionOptional.isEmpty())
            throw new RequestException("The question does not exist", HttpStatus.NOT_FOUND);

        Optional<Option> optionOptional = optionService.get(id);
        if (optionOptional.isEmpty())
            throw new RequestException("The option does not exist", HttpStatus.NOT_FOUND);

        Option option = optionOptional.get();
        option.setOptionText(optionDto.getOptionText());
        option.setQuestion(questionOptional.get());

        optionService.upsert(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option saved");

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<?>> delete(@PathVariable String id) {
        Optional<Option> optionOptional = optionService.get(id);
        if (optionOptional.isEmpty())
            throw new RequestException("The option does not exist", HttpStatus.NOT_FOUND);

        Option option = optionOptional.get();
        optionService.remove(option);

        ResponseDTO<?> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("Option removed");

        return ResponseEntity.ok(responseDTO);
    }

}
