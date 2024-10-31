package com.library.rbc.exceptionHandler;

public class StatusBadRequestException extends RuntimeException {

  public StatusBadRequestException(String message) {
    super(message);
  }
}
