package com.poolplatform.features.user.domain.models;

public class UserSummary {
    private String id;
    private String username;
    private String email;
    private int visits;
    private int answers;
    private int surveys;

    public UserSummary() {
    }

    public UserSummary(String id, String username, String email, int visits, int answers, int surveys) {
        this.id = id;
        this.username = username;
        this.email = email;
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

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public int getSurveys() {
        return surveys;
    }

    public void setSurveys(int surveys) {
        this.surveys = surveys;
    }

}
