package com.nicolasperussi.scriptburger.exceptions;

import java.time.Instant;
import java.util.List;

public class ValidationErrorMessage extends ErrorMessage {
  List<String> errors;

  public ValidationErrorMessage(int statusCode, Instant timestamp, List<String> errors) {
    super(statusCode, timestamp);
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }
}