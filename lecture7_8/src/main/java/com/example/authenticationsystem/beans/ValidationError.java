package com.example.authenticationsystem.beans;

public class ValidationError {

    private String message;

    public ValidationError() {
    }

    public ValidationError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

}
