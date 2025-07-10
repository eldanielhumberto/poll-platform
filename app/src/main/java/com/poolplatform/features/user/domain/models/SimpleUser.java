package com.poolplatform.features.user.domain.models;

public class SimpleUser {
    private String email;

    public SimpleUser() {
    }

    public SimpleUser(User user) {
        this.email = user.getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
