package com.blogpessoal.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidationError extends StandardError {

    private Map<String, String> errors;

    public ValidationError() {}

    public ValidationError(LocalDateTime timestamp, Integer status, String error, String message, String path, Map<String, String> errors) {
        super(timestamp, status, error, message, path);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}