package com.poolplatform.features.option.adapters.mappers;

import java.util.Map;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.entities.QuestionEntity;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.question.domain.models.Question;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.models.Survey;

public class OptionMapper {
    public static Option toOption(OptionEntity optionEntity) {
        Option option = new Option();
        option.setId(optionEntity.getId());
        option.setOptionText(optionEntity.getOptionText());
        option.setQuestion(QuestionMapper.toQuestion(optionEntity.getQuestion()));
        option.setSurvey(SurveyMapper.toSurvey(optionEntity.getSurvey()));

        return option;
    }

    public static OptionEntity toOptionEntity(Option option) {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setId(option.getId());
        optionEntity.setOptionText(option.getOptionText());
        optionEntity.setQuestion(QuestionMapper.toQuestionEntity(option.getQuestion()));
        optionEntity.setSurvey(SurveyMapper.toSurveyEntity(option.getSurvey()));

        return optionEntity;
    }

    public static Option toSimpleOption(OptionEntity option) {
        Question question = new Question();
        question.setId(option.getQuestion().getId());

        Survey survey = new Survey();
        survey.setId(option.getSurvey().getId());

        Option newOption = new Option();
        newOption.setId(option.getId());
        newOption.setOptionText(option.getOptionText());
        newOption.setQuestion(question);
        newOption.setSurvey(survey);

        return newOption;
    }

    public static OptionEntity toSimpleOptionEntity(Option option) {
        QuestionEntity question = new QuestionEntity();
        question.setId(option.getQuestion().getId());

        SurveyEntity survey = new SurveyEntity();
        survey.setId(option.getSurvey().getId());

        OptionEntity newOption = new OptionEntity();
        newOption.setId(option.getId());
        newOption.setOptionText(option.getOptionText());
        newOption.setQuestion(question);
        newOption.setSurvey(survey);

        return newOption;
    }

    public static Option mapOptionFromEntity(OptionEntity optionEntity, Map<String, Option> optionEntityMap) {
        Option option;
        if (optionEntityMap.containsKey(optionEntity.getId())) {
            option = optionEntityMap.get(optionEntity.getId());
        } else {
            option = OptionMapper.toSimpleOption(optionEntity);
            optionEntityMap.put(optionEntity.getId(), option);
        }

        return option;
    }

    public static OptionEntity mapOption(Option o, Map<String, OptionEntity> optionEntityMap) {
        OptionEntity option;
        if (optionEntityMap.containsKey(o.getId())) {
            option = optionEntityMap.get(o.getId());
        } else {
            option = OptionMapper.toSimpleOptionEntity(o);
            optionEntityMap.put(o.getId(), option);
        }
        return option;
    }
}
