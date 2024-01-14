package com.nicolasperussi.scriptburger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_FOUND.value(),
        Instant.now(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        Instant.now(),
        ex.getMessage(),
        request.getDescription(false));

    ex.printStackTrace();

    return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, WebRequest request) {
    List<String> errors = new ArrayList<>();

    ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

    ValidationErrorMessage message = new ValidationErrorMessage(
        HttpStatus.BAD_REQUEST.value(),
        Instant.now(),
        errors);

    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }
}