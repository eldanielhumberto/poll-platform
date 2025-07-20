package com.poolplatform.features.answer.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.answer.domain.AnswerService;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

@Service
public class AnswerApplication implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> get(Survey survey, User user) {
        return answerRepository.get(survey, user);
    }

    @Override
    public List<Answer> get(Survey survey) {
        return answerRepository.get(survey);
    }

    @Override
    public void upsertAll(List<Answer> answers) {
        answerRepository.upsertAll(answers);
    }

    @Override
    public void remove(Answer answer) {
        answerRepository.remove(answer);
    }

}
