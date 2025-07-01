package com.poolplatform.features.answer.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.answer.domain.AnswerService;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

@Service
public class AnswerApplication implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> get() {
        return answerRepository.get();
    }

    @Override
    public Optional<Answer> get(String id) {
        return answerRepository.get(id);
    }

    @Override
    public List<Answer> get(Survey survey) {
        return answerRepository.get(survey);
    }

    @Override
    public List<Answer> get(Question question) {
        return answerRepository.get(question);
    }

    @Override
    public Answer upsert(Answer answer) {
        return answerRepository.upsert(answer);
    }

    @Override
    public void remove(Answer answer) {
        answerRepository.remove(answer);
    }

}
