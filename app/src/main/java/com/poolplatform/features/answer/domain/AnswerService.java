package com.poolplatform.features.answer.domain;

import java.util.List;
import java.util.Optional;

import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public interface AnswerService {
    List<Answer> get();

    Optional<Answer> get(String id);

    List<Answer> get(Survey survey, User user);

    List<Answer> get(Question question);

    Answer upsert(Answer answer);

    void upsertAll(List<Answer> answers);

    void remove(Answer answer);
}
