package com.example.fetchtakehomeexercise.interceptor;

import com.example.fetchtakehomeexercise.exception.NotFoundException;
import com.example.fetchtakehomeexercise.model.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionInterceptor {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        log.error("No receipt found for that ID: {}", ex.getId());
        ErrorResponse response = new ErrorResponse();
        response.setDescription(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleRequestBodyException(Exception ex) {
        log.error("The receipt is invalid.");
        ErrorResponse response = new ErrorResponse();
        if (ex instanceof MethodArgumentNotValidException || ex instanceof HttpMessageNotReadableException) {
            response.setDescription("The receipt is invalid.");
        } else {
            response.setDescription("Unknown exception.");
        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Bad formatted receipt Id.");
        ErrorResponse response = new ErrorResponse();
        response.setDescription("Bad formatted receipt Id.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
