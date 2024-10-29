package com.library.rbc.exceptionhandler;

public class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(String message) {
    super(message);
  }
}
