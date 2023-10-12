package com.eval.cediaz.evaljava.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ValidationErrorResponse> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();

        for (ObjectError objectError  : e.getBindingResult().getAllErrors()) {
            error.getErrors().add(new ErrorMessage(objectError.getObjectName(), objectError.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    ResponseEntity<ValidationErrorResponse> onUserException(
            UserException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(new ErrorMessage("Email", e.getErrorMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseEntity<ValidationErrorResponse> onIllegalArgumentException(
            IllegalArgumentException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(new ErrorMessage("Database", e.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ResponseEntity<ValidationErrorResponse> onHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getErrors().add(new ErrorMessage("Any", e.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}