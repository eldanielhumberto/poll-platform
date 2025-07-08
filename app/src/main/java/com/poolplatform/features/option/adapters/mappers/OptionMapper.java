package com.poolplatform.features.option.adapters.mappers;

import com.poolplatform.features.option.adapters.entities.OptionEntity;
import com.poolplatform.features.option.domain.models.Option;
import com.poolplatform.features.question.adapters.mappers.QuestionMapper;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;

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
}
