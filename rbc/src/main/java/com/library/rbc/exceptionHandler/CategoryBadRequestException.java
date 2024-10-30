package com.library.rbc.exceptionHandler;

public class CategoryBadRequestException extends RuntimeException {

  public CategoryBadRequestException(String message) {
    super(message);
  }
}
