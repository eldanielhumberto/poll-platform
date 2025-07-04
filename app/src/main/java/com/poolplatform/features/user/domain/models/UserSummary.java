package com.poolplatform.features.user.domain.models;

public class UserSummary {
    private String id;
    private String username;
    private String email;
    private int visits;

    public UserSummary(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();

        if (user.getVisits() != null) {
            this.visits = user.getVisits().size();
        }
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

}
