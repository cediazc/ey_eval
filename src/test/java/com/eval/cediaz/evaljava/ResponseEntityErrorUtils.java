package com.eval.cediaz.evaljava;

import com.eval.cediaz.evaljava.exception.ErrorMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseEntityErrorUtils {

    @JsonProperty("errors")
    private List<ErrorMessage> errors;

    public ResponseEntityErrorUtils() {
    }

    public ResponseEntityErrorUtils(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ResponseEntityErrorUtils{" +
                "errors=" + errors +
                '}';
    }
}
