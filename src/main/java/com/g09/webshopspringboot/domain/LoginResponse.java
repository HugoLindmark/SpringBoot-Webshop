package com.g09.webshopspringboot.domain;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse {

    private boolean verified;
    private User user;


    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public LoginResponse(boolean verified, User user) {
        this.verified = verified;
        this.user = user;
    }

    public LoginResponse() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
