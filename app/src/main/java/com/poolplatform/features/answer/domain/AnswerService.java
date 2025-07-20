package com.poolplatform.features.answer.domain;

import java.util.List;

import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

public interface AnswerService {
    List<Answer> get(Survey survey, User user);

    List<Answer> get(Survey survey);

    void upsertAll(List<Answer> answers);

    void remove(Answer answer);
}
