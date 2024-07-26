package com.example.greenbookbackend.models;

import org.springframework.http.HttpStatus;

public class GreenBookApiException extends RuntimeException{

    private final HttpStatus status;
    private final String message;

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GreenBookApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
