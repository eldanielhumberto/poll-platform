package com.poolplatform.features.survey.application;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.answer.domain.AnswerRepository;
import com.poolplatform.features.option.domain.OptionRepository;
import com.poolplatform.features.question.domain.QuestionRepository;
import com.poolplatform.features.survey.domain.SurveyRepository;
import com.poolplatform.features.survey.domain.SurveyService;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.domain.VisitRepository;

@Service
public class SurveyApplication implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Survey> get() {
        return surveyRepository.get();
    }

    @Override
    public List<Survey> get(User user) {
        return surveyRepository.get(user);
    }

    @Override
    public Optional<Survey> get(String id) {
        return surveyRepository.get(id);
    }

    @Override
    public Survey upsert(Survey survey) {
        return surveyRepository.upsert(survey);
    }

    @Override
    public void remove(Survey survey) {
        if (!survey.getAnswers().isEmpty())
            answerRepository.deleteAnswersBySurveyId(survey);

        if (!survey.getVisits().isEmpty())
            visitRepository.deleteVisitsBySurveyId(survey);

        if (!survey.getOptions().isEmpty())
            optionRepository.deleteOptionsBySurveyId(survey);

        if (!survey.getQuestions().isEmpty())
            questionRepository.deleteQuestionsBySurveyId(survey);

        surveyRepository.remove(survey);
    }
}
