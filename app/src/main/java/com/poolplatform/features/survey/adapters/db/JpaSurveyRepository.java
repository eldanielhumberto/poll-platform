package com.poolplatform.features.survey.adapters.db;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.survey.adapters.mappers.SurveyMapper;
import com.poolplatform.features.survey.domain.SurveyRepository;
import com.poolplatform.features.survey.domain.models.Survey;
import com.poolplatform.features.user.domain.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface JpaSurveyRepository extends JpaRepository<SurveyEntity, String>, SurveyRepository {
    @Override
    default List<Survey> get() {
        return findAll().stream().map(SurveyMapper::toSurvey).collect(Collectors.toList());
    }

    @Override
    default List<Survey> get(User user) {
        return findByUserId(user.getId()).stream().map(SurveyMapper::toSurvey).collect(Collectors.toList());
    }

    @Override
    default Optional<Survey> get(String id) {
        return findById(id).map(SurveyMapper::toSurvey);
    }

    @Override
    default Survey upsert(Survey survey) {
        return SurveyMapper.toSurvey(save(SurveyMapper.toSurveyEntity(survey)));
    }

    @Override
    default void remove(Survey survey) {
        delete(SurveyMapper.toSurveyEntity(survey));
    }

    @Override
    default void reset(Survey survey, User user) {
        resetSurvey(survey.getId(), user.getId());
    }

    @NativeQuery(value = "SELECT * FROM surveys WHERE user_id = ?1")
    List<SurveyEntity> findByUserId(String user_id);

    @Modifying
    @Transactional
    @NativeQuery(value = "DELETE FROM answers WHERE survey_id = ?1 AND user_id = ?2")
    void resetSurvey(String survey_id, String user_id);
}
