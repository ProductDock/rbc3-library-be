package com.library.rbc.controller.usercontroller;

import com.library.rbc.model.dto.RoleDto;
import com.library.rbc.model.dto.UserDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class UserControllerSetUp {

  static final String USER_ID = "1";
  static final String USER_FULL_NAME = "Full name";
  static final String USER_GOOGLE_ID = "2";
  static final String USER_EMAIL = "email";
  static final String USER_IMAGE_URL = "image";
  static final String USER_ROLE = "EMPLOYEE";

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

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

  static Page<UserDto> createUserDtos() {
    UserDto userDto = createUserDto();
    List<UserDto> userDtos = List.of(userDto);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(userDtos, pageable, userDtos.size());
  }
}
