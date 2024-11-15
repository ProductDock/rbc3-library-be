package com.library.rbc.exceptionhandler;

public class CategoryBadRequestException extends RuntimeException {

  public CategoryBadRequestException(String message) {
    super(message);
  }
}
