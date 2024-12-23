package com.library.rbc.integration.usercontroller;

import com.library.rbc.model.User;
import com.library.rbc.model.dto.RoleDto;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.enums.Role;

public class UserSetUp {

  static final String USER_ID = "1";
  static final String USER_FULL_NAME = "Full name";
  static final String USER_GOOGLE_ID = "2";
  static final String USER_EMAIL = "email";
  static final String USER_IMAGE_URL = "image";
  static final String USER_ROLE = "EMPLOYEE";

  static final String USER_2_ID = "2";
  static final String USER_2_ROLE = "ADMIN";
  
  static User createUser() {
    return User.builder()
        .id(USER_ID)
        .fullName(USER_FULL_NAME)
        .email(USER_EMAIL)
        .imageUrl(USER_IMAGE_URL)
        .googleID(USER_GOOGLE_ID)
        .role(Role.EMPLOYEE)
        .build();
  }

  static User createUser2() {
    return User.builder()
        .id(USER_2_ID)
        .fullName(USER_FULL_NAME)
        .email(USER_EMAIL)
        .imageUrl(USER_IMAGE_URL)
        .role(Role.ADMIN)
        .build();
  }

  static UserDto createUserDto() {
    return UserDto.builder()
        .id(USER_ID)
        .fullName(USER_FULL_NAME)
        .googleID(USER_GOOGLE_ID)
        .email(USER_EMAIL)
        .imageUrl(USER_IMAGE_URL)
        .role(RoleDto.EMPLOYEE)
        .build();
  }
}
