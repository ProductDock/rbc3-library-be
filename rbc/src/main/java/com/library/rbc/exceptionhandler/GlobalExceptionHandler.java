package com.library.rbc.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse handleException(BookNotFoundException ex) {
    return new ErrorResponse(ex.getMessage());
  }

  @ExceptionHandler(value = ImageUploadException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(ImageUploadException ex) {
    return new ErrorResponse(ex.getMessage());
  }
}

