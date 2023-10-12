package com.eval.cediaz.evaljava.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {

    private List<ErrorMessage> errorMessages = new ArrayList<>();

    public List<ErrorMessage> getErrors() {
        return errorMessages;
    }

    public void setErrors(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
