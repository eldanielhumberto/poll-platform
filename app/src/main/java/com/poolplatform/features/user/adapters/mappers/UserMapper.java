package com.poolplatform.features.user.adapters.mappers;

import java.util.stream.Collectors;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.answer.domain.models.Answer;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.adapters.entities.UserEntity;
import com.poolplatform.features.user.domain.models.SimpleUser;
import com.poolplatform.features.user.domain.models.User;
import com.poolplatform.features.user.domain.models.UserResponse;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;
import com.poolplatform.features.visit.domain.models.Visit;

public class UserMapper {
    public static User toUser(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId());
        user.setUsername(userEntity.getUsername());
        user.setEmail(userEntity.getEmail());
        user.setPassword(userEntity.getPassword());

        if (userEntity.getVisits() != null) {
            user.setVisits(userEntity.getVisits().stream().map(v -> {
                Visit visit = new Visit();
                visit.setId(v.getId());

                return visit;
            }).collect(Collectors.toList()));
        }

        if (userEntity.getAnswers() != null) {
            user.setAnswers(userEntity.getAnswers().stream().map(a -> {
                Answer answer = new Answer();
                answer.setId(a.getId());

                return answer;
            }).collect(Collectors.toList()));
        }

        if (userEntity.getSurveys() != null) {
            user.setSurveys(userEntity.getSurveys().stream().map(s -> {
                Survey survey = new Survey();
                survey.setId(s.getId());

                return survey;
            }).collect(Collectors.toList()));
        }

        return user;
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setUsername(user.getUsername());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        if (user.getVisits() != null) {
            userEntity.setVisits(user.getVisits().stream().map(v -> {
                VisitEntity visit = new VisitEntity();
                visit.setId(v.getId());

                return visit;
            }).collect(Collectors.toList()));
        }

        if (user.getAnswers() != null) {
            userEntity.setAnswers(user.getAnswers().stream().map(a -> {
                AnswerEntity answer = new AnswerEntity();
                answer.setId(a.getId());

                return answer;
            }).collect(Collectors.toList()));
        }

        if (user.getSurveys() != null) {
            userEntity.setSurveys(user.getSurveys().stream().map(s -> {
                SurveyEntity survey = new SurveyEntity();
                survey.setId(s.getId());

                return survey;
            }).collect(Collectors.toList()));
        }

        return userEntity;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse userSummary = new UserResponse();
        userSummary.setId(user.getId());
        userSummary.setUsername(user.getUsername());
        userSummary.setEmail(user.getEmail());
        userSummary.setVisits(user.getVisits().size());
        userSummary.setAnswers(user.getAnswers().size());
        userSummary.setSurveys(user.getSurveys().size());

        return userSummary;
    }

    public static SimpleUser toSimpleUser(User user) {
        SimpleUser simpleUser = new SimpleUser();
        simpleUser.setEmail(user.getEmail());

        return simpleUser;
    }
}
