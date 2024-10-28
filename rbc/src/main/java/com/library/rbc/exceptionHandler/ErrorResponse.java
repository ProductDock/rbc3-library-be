package com.library.rbc.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
  
  private int statusCode;
  private String message;

  public ErrorResponse(String message) {
    super();
    this.message = message;
  }
}