package com.eval.cediaz.evaljava.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorMessage {

    private final String fieldName;

    private final String message;

    public ErrorMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
