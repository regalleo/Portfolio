package com.portfolio.dto;

public class InterestRequest {
    private String email;

    public InterestRequest() {}

    public InterestRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
