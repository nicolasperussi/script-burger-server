package com.nicolasperussi.scriptburger.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
  private int statusCode;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
  private Instant timestamp;
  private String message;
  private String description;

  public ErrorMessage(int statusCode, Instant timestamp, String message, String description) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
    this.message = message;
    this.description = description;
  }

  public ErrorMessage(int statusCode, Instant timestamp) {
    this.statusCode = statusCode;
    this.timestamp = timestamp;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public Instant getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public String getDescription() {
    return description;
  }
}