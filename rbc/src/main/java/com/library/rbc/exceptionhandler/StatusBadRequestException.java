package com.library.rbc.exceptionhandler;

public class StatusBadRequestException extends RuntimeException {

  public StatusBadRequestException(String message) {
    super(message);
  }
}
