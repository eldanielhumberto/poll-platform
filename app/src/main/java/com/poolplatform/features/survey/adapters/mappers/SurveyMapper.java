package com.poolplatform.features.survey.adapters.mappers;

import java.util.stream.Collectors;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.adapters.mappers.OptionMapper;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.survey.domain.models.SurveySummary;
import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.adapters.mappers.UserMapper;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.Visit;

public class SurveyMapper {
    public static Survey toSurvey(SurveyEntity surveyEntity) {
        java.util.Map<String, Option> optionEntityMap = new java.util.HashMap<>();

        Survey survey = new Survey();
        survey.setId(surveyEntity.getId());
        survey.setTitle(surveyEntity.getTitle());
        survey.setDescription(surveyEntity.getDescription());
        survey.setAuthor(UserMapper.toUser(surveyEntity.getAuthor()));
        survey.setCreatedAt(surveyEntity.getCreatedAt());

        if (surveyEntity.getQuestions() != null) {
            survey.setQuestions(surveyEntity.getQuestions().stream().map(q -> {
                User author = UserMapper.toUser(q.getAuthor());

                Survey surveyForQuestion = new Survey();
                surveyForQuestion.setId(q.getSurvey().getId());

                Question question = new Question();
                question.setId(q.getId());
                question.setQuestionText(q.getQuestionText());
                question.setAuthor(author);
                question.setSurvey(surveyForQuestion);
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
            }).toList());
        }

        if (surveyEntity.getVisits() != null) {
            survey.setVisits(surveyEntity.getVisits().stream().map(v -> {
                Visit visit = new Visit();
                visit.setId(v.getId());
                return visit;
            }).collect(Collectors.toList()));
        }

        if (surveyEntity.getOptions() != null) {
            survey.setOptions(surveyEntity.getOptions().stream().map(o -> {
                Option option;
                if (optionEntityMap.containsKey(o.getId())) {
                    option = optionEntityMap.get(o.getId());
                } else {
                    option = OptionMapper.toSimpleOption(o);
                    optionEntityMap.put(o.getId(), option);
                }

                return option;
            }).collect(Collectors.toList()));
        }

        if (surveyEntity.getAnswers() != null) {
            survey.setAnswers(surveyEntity.getAnswers().stream().map(a -> {
                Answer answer = new Answer();
                answer.setId(a.getId());
                return answer;
            }).collect(Collectors.toList()));
        }

        return survey;
    }

    public static SurveyEntity toSurveyEntity(Survey survey) {
        java.util.Map<String, OptionEntity> optionEntityMap = new java.util.HashMap<>();

        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setId(survey.getId());
        surveyEntity.setTitle(survey.getTitle());
        surveyEntity.setDescription(survey.getDescription());
        surveyEntity.setAuthor(UserMapper.toUserEntity(survey.getAuthor()));
        surveyEntity.setCreatedAt(survey.getCreatedAt());

        // Set questions
        if (survey.getQuestions() != null) {
            // Usar un mapa para evitar instancias duplicadas de OptionEntity por ID
            surveyEntity.setQuestions(survey.getQuestions().stream().map(q -> {
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
            }).toList());
        }

        // Set visits
        if (survey.getVisits() != null) {
            surveyEntity.setVisits(survey.getVisits().stream().map(v -> {
                VisitEntity visit = new VisitEntity();
                visit.setId(v.getId());
                return visit;
            }).collect(Collectors.toList()));
        }

        // Set options
        if (survey.getOptions() != null) {
            surveyEntity.setOptions(survey.getOptions().stream().map(o -> {
                OptionEntity option;
                if (optionEntityMap.containsKey(o.getId())) {
                    option = optionEntityMap.get(o.getId());
                } else {
                    option = OptionMapper.toSimpleOptionEntity(o);
                    optionEntityMap.put(o.getId(), option);
                }
                return option;
            }).collect(Collectors.toList()));
        }

        // Set answers
        if (survey.getAnswers() != null) {
            surveyEntity.setAnswers(survey.getAnswers().stream().map(a -> {
                AnswerEntity answer = new AnswerEntity();
                answer.setId(a.getId());
                return answer;
            }).collect(Collectors.toList()));
        }

        return surveyEntity;
    }

    public static SurveySummary toSurveySummary(Survey survey) {
        SurveySummary surveySummary = new SurveySummary();
        surveySummary.setId(survey.getId());
        surveySummary.setTitle(survey.getTitle());
        surveySummary.setDescription(survey.getDescription());
        surveySummary.setAuthor(UserMapper.toUserSummary(survey.getAuthor()));
        surveySummary.setVisits(survey.getVisits().size());
        surveySummary.setAnswers(survey.getAnswers().size());
        surveySummary.setCreatedAt(survey.getCreatedAt());

        return surveySummary;
    }

}
