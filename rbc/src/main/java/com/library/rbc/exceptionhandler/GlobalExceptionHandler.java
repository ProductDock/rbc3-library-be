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

  @ExceptionHandler(value = CategoryBadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(CategoryBadRequestException ex) {
    return new ErrorResponse(ex.getMessage());
  }

  @ExceptionHandler(value = StatusBadRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(StatusBadRequestException ex) {
    return new ErrorResponse(ex.getMessage());
  }

  @ExceptionHandler(value = ImageUploadException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(ImageUploadException ex) {
    return new ErrorResponse(ex.getMessage());
  }

  @ExceptionHandler(value = ContentTypeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(ContentTypeException ex) {
    return new ErrorResponse(ex.getMessage());
  }

  @ExceptionHandler(value = EmailAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorResponse handleException(EmailAlreadyExistsException ex) {
    return new ErrorResponse(ex.getMessage());
  }

}

