package com.poolplatform.features.auth.adapters.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupDTO {
    @NotNull
    @Size(min = 4, max = 16)
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 4, max = 8)
    private String password;

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
}
