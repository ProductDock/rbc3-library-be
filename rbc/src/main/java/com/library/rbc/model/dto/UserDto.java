package com.library.rbc.model.dto;

import com.library.rbc.model.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

  private String fullName;
  private String googleID;
  private String email;
  private String imageUrl;
  private Role role;

}
