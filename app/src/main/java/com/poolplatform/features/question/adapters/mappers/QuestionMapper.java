package com.poolplatform.features.question.adapters.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.option.domain.models.SimpleOption;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.question.domain.models.QuestionResponse;
import com.poolplatform.features.question.domain.models.QuestionResponseWithSurvey;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.models.User;

public class QuestionMapper {
    public static Question toQuestion(QuestionEntity questionEntity) {
        Question question = new Question();
        question.setId(questionEntity.getId());
        question.setQuestionText(questionEntity.getQuestionText());
        question.setSurvey(SurveyMapper.toSurvey(questionEntity.getSurvey()));
        question.setAuthor(UserMapper.toUser(questionEntity.getAuthor()));

        if (questionEntity.getOptions() != null) {
            question.setOptions(questionEntity.getOptions().stream().map(o -> {
                Survey surveyEntity = new Survey();
                surveyEntity.setId(o.getSurvey().getId());

                Question questionEntityForOption = new Question();
                questionEntityForOption.setId(o.getQuestion().getId());

                Option option = new Option();
                option.setId(o.getId());
                option.setOptionText(o.getOptionText());
                option.setSurvey(surveyEntity);
                option.setQuestion(questionEntityForOption);

                return option;
            }).collect(Collectors.toList()));
        }

        return question;
    }

    public static QuestionEntity toQuestionEntity(Question question) {
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setId(question.getId());
        questionEntity.setQuestionText(question.getQuestionText());
        questionEntity.setSurvey(SurveyMapper.toSurveyEntity(question.getSurvey()));
        questionEntity.setAuthor(UserMapper.toUserEntity(question.getAuthor()));

        if (question.getOptions() != null) {
            questionEntity.setOptions(question.getOptions().stream().map(o -> {
                SurveyEntity surveyEntity = new SurveyEntity();
                surveyEntity.setId(o.getSurvey().getId());

                QuestionEntity questionEntityForOption = new QuestionEntity();
                questionEntityForOption.setId(o.getQuestion().getId());

                OptionEntity option = new OptionEntity();
                option.setId(o.getId());
                option.setOptionText(o.getOptionText());
                option.setSurvey(surveyEntity);
                option.setQuestion(questionEntityForOption);

                return option;
            }).collect(Collectors.toList()));

        }

        return questionEntity;
    }

    /**
     * Converts a Question object to a QuestionResponse object.
     *
     * @param question The Question object to convert.
     * @return A QuestionResponse object containing the question text and options.
     */
    public static QuestionResponse toQuestionResponse(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setQuestionText(question.getQuestionText());

        if (question.getOptions() != null) {
            questionResponse.setOptions(question.getOptions().stream()
                    .map(o -> {
                        SimpleOption option = new SimpleOption();
                        option.setId(o.getId());
                        option.setOptionText(o.getOptionText());

                        return option;
                    }).collect(Collectors.toList()));
        }

        return questionResponse;
    }

    /**
     * Converts a Question object to a QuestionResponseWithSurvey object.
     *
     * @param question The Question object to convert.
     * @return A QuestionResponseWithSurvey object containing the question and its
     *         survey.
     */
    public static QuestionResponseWithSurvey toQuestionResponseWithSurvey(Question question) {
        QuestionResponseWithSurvey questionResponse = new QuestionResponseWithSurvey();
        questionResponse.setId(question.getId());
        questionResponse.setQuestionText(question.getQuestionText());

        if (question.getOptions() != null) {
            questionResponse.setOptions(question.getOptions().stream()
                    .map(o -> {
                        SimpleOption option = new SimpleOption();
                        option.setId(o.getId());
                        option.setOptionText(o.getOptionText());

                        return option;
                    }).collect(Collectors.toList()));
        }

        if (question.getSurvey() != null) {
            questionResponse.setSurvey(SurveyMapper.toSimpleSurvey(question.getSurvey()));
        }

        return questionResponse;
    }

    /**
     * Converts a list of Question objects to a list of QuestionEntity objects.
     *
     * @param questions The list of Question objects to convert.
     * @return A list of QuestionEntity objects.
     */
    public static List<QuestionEntity> toQuestionEntities(List<Question> questions) {
        List<QuestionEntity> questionEntities = new ArrayList<>();
        for (Question question : questions) {
            QuestionEntity questionEntity = new QuestionEntity();
            questionEntity.setQuestionText(question.getQuestionText());
            questionEntity.setSurvey(SurveyMapper.toSurveyEntity(question.getSurvey()));
            questionEntity.setAuthor(UserMapper.toUserEntity(question.getAuthor()));

            List<OptionEntity> optionEntities = new ArrayList<>();
            for (Option option : question.getOptions()) {
                OptionEntity optionEntity = new OptionEntity();
                optionEntity.setOptionText(option.getOptionText());
                optionEntity.setSurvey(SurveyMapper.toSurveyEntity(option.getSurvey()));
                optionEntity.setQuestion(questionEntity);
                optionEntities.add(optionEntity);
            }
            questionEntity.setOptions(optionEntities);
            questionEntities.add(questionEntity);
        }

        return questionEntities;
    }

    /**
     * Maps a QuestionEntity object to a Question object, using a map to avoid
     * duplicate Option objects.
     *
     * @param q               The QuestionEntity object to map.
     * @param optionEntityMap A map of Option objects to avoid duplicates.
     * @return The mapped Question object.
     */
    public static Question mapQuestionsFromEntity(QuestionEntity q, Map<String, Option> optionEntityMap) {
        User author = UserMapper.toUser(q.getAuthor());

        Survey surveyForQuestion = new Survey();
        surveyForQuestion.setId(q.getSurvey().getId());

        Question question = new Question();
        question.setId(q.getId());
        question.setQuestionText(q.getQuestionText());
        question.setAuthor(author);
        question.setSurvey(surveyForQuestion);

        // For avoid duplicates in options
        question.setOptions(q.getOptions().stream().map(o -> {
            Option option;
            if (optionEntityMap.containsKey(o.getId())) {
                option = optionEntityMap.get(o.getId());
            } else {
                option = OptionMapper.toSimpleOption(o);
                optionEntityMap.put(o.getId(), option);
            }

            return option;
        }).toList());

        return question;
    }

    /**
     * Maps a Question object to a QuestionEntity object, using a map to avoid
     * duplicate OptionEntity objects.
     *
     * @param q               The Question object to map.
     * @param optionEntityMap A map of OptionEntity objects to avoid duplicates.
     * @return The mapped QuestionEntity object.
     */
    public static QuestionEntity mapQuestion(Question q, Map<String, OptionEntity> optionEntityMap) {
        UserEntity author = UserMapper.toUserEntity(q.getAuthor());

        SurveyEntity surveyForQuestion = new SurveyEntity();
        surveyForQuestion.setId(q.getSurvey().getId());

        QuestionEntity question = new QuestionEntity();
        question.setId(q.getId());
        question.setQuestionText(q.getQuestionText());
        question.setAuthor(author);
        question.setSurvey(surveyForQuestion);

        // Set options
        question.setOptions(q.getOptions().stream().map(o -> {
            OptionEntity option;
            if (optionEntityMap.containsKey(o.getId())) {
                option = optionEntityMap.get(o.getId());
            } else {
                option = OptionMapper.toSimpleOptionEntity(o);
                optionEntityMap.put(o.getId(), option);
            }
            return option;
        }).toList());

        return question;
    }
}
