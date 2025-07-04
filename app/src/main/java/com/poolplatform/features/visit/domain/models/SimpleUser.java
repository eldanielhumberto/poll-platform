package com.poolplatform.features.visit.domain.models;

import com.poolplatform.features.user.domain.models.User;

public class SimpleUser {
    private String email;

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
