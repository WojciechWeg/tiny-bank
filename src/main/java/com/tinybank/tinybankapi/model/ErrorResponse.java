package com.tinybank.tinybankapi.model;

import java.time.LocalDateTime;

public class ErrorResponse {

    LocalDateTime time;
    String status;
    String error;
    String message;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime time, String status, String error, String message) {
        this.time = time;
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
