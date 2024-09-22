package com.example.demo.core.exception;

import java.util.Map;

public class InvalidParametersException extends RuntimeException {

    private String message;
    private Map<String, Object> errors;

    public InvalidParametersException(String message) {
        super(message);
    }

    public InvalidParametersException(String message, Map<String, Object> errors) {
        this.message = message;
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Map<String, Object> getErrors() {
        return errors;
    }
}
