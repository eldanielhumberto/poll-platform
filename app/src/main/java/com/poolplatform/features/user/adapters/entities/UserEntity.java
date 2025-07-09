package com.poolplatform.features.user.adapters.entities;

import java.util.ArrayList;
import java.util.List;

import com.poolplatform.features.answer.adapters.entities.AnswerEntity;
import com.poolplatform.features.survey.adapters.entities.SurveyEntity;
import com.poolplatform.features.visit.adapters.entities.VisitEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "visited", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<VisitEntity> visits = new ArrayList<VisitEntity>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<AnswerEntity> answers;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SurveyEntity> surveys;

    public UserEntity() {
    }

    public UserEntity(String id, String username, String email, String password, List<VisitEntity> visits,
            List<AnswerEntity> answers, List<SurveyEntity> surveys) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.visits = visits;
        this.answers = answers;
        this.surveys = surveys;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VisitEntity> getVisits() {
        return visits;
    }

    public void setVisits(List<VisitEntity> visits) {
        this.visits = visits;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public List<SurveyEntity> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<SurveyEntity> surveys) {
        this.surveys = surveys;
    }

}
