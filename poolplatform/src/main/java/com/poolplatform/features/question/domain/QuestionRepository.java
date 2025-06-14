package com.poolplatform.features.question.domain;

import java.util.List;

import com.poolplatform.domain.interfaces.Crud;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.domain.models.Survey;

public interface QuestionRepository extends Crud<Question> {
    List<Question> get(Survey survey);
}
