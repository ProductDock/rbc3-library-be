package com.library.rbc.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private String id;
  private String fullName;
  private String googleID;
  private String email;
  private String imageUrl;
  private RoleDto role;

}
