package com.g09.webshopspringboot.domain;

public class LogoutResponse {

    private String response;

    public LogoutResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
