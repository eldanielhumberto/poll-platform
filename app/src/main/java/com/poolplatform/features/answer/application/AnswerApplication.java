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
import com.poolplatform.features.user.domain.models.User;

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
    public List<Answer> get(Survey survey, User user) {
        return answerRepository.get(survey, user);
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
    public void upsertAll(List<Answer> answers) {
        answerRepository.upsertAll(answers);
    }

    @Override
    public void remove(Answer answer) {
        answerRepository.remove(answer);
    }

}
