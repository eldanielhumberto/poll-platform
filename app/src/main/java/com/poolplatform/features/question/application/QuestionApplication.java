package com.poolplatform.features.question.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.dto.QuestionForSaveAllDTO;
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
    public Optional<Question> getByText(String questionText, String surveyId) {
        return questionRepository.getByText(questionText, surveyId);
    }

    @Override
    public void remove(Question t) {
        questionRepository.remove(t);
    }

    @Override
    public Question upsert(Question t) {
        return questionRepository.upsert(t);
    }

    @Override
    public void saveAll(List<QuestionForSaveAllDTO> saveAllQuestionsDTO, Survey survey) {
        // Set questions and options
        List<Question> questions = new ArrayList<>();
        for (QuestionForSaveAllDTO questionForSave : saveAllQuestionsDTO) {
            Optional<Question> question = getByText(questionForSave.getQuestionText(),
                    survey.getId());

            if (question.isPresent()) {
                continue; // If question already exists, skip to next
            }

            Question newQuestion = new Question();
            newQuestion.setQuestionText(questionForSave.getQuestionText());
            newQuestion.setAuthor(survey.getAuthor());
            newQuestion.setSurvey(survey);

            // Set options for the question
            List<Option> options = new ArrayList<>();
            for (String optionText : questionForSave.getOptions()) {
                Option newOption = new Option();
                newOption.setOptionText(optionText);
                newOption.setQuestion(newQuestion);
                newOption.setSurvey(survey);
                options.add(newOption);
            }

            newQuestion.setOptions(options);
            questions.add(newQuestion);
        }

        questionRepository.saveAll(questions);
    }

}
