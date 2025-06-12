package com.poolplatform.features.question.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.question.domain.QuestionRepository;
import com.poolplatform.features.question.domain.QuestionService;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

@Service
public class QuestionApplication implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> get() {
        return questionRepository.get();
    }

    @Override
    public List<Question> get(Survey survey) {
        return questionRepository.get(survey);
    }

    @Override
    public Optional<Question> get(String id) {
        return questionRepository.get(id);
    }

    @Override
    public void remove(Question t) {
        questionRepository.remove(t);
    }

    @Override
    public Question upsert(Question t) {
        return questionRepository.upsert(t);
    }

}
