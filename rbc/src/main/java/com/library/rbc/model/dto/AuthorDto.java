package com.library.rbc.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {

  private String id;
  private String fullName;
}
