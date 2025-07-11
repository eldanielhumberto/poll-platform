package com.poolplatform.features.user.domain.models;

public class SimpleUser {
    private String username;
    private String email;

    public SimpleUser() {
    }

    public SimpleUser(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
