package com.eval.cediaz.evaljava.exception;

public class UserNotFoundException extends RuntimeException {

    private String errorMessage;

    public UserNotFoundException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "UserException{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
