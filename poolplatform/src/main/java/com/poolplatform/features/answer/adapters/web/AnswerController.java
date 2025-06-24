package com.poolplatform.features.answer.adapters.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poolplatform.adapters.dto.ResponseDTO;
import com.poolplatform.features.answer.domain.AnswerService;
import com.poolplatform.features.answer.domain.models.Answer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/answers")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @GetMapping("/get")
    public ResponseEntity<?> getAll(@RequestParam(required = false) String param) {
        List<Answer> answers = answerService.get();

        ResponseDTO<List<Answer>> responseDTO = new ResponseDTO<>();
        responseDTO.setMessage("All answers in the app");
        responseDTO.setData(answers);

        return ResponseEntity.ok(responseDTO);
    }

}
