package com.poolplatform.features.user.domain.models;

import java.util.List;

import com.poolplatform.features.visit.domain.models.Visit;

public class User {
    private String id;
    private String username;
    private String email;
    private String password;
    private List<Visit> visits;

    public User() {
    }

    public User(String id, String username, String email, String password, List<Visit> visits) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.visits = visits;
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

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

}
