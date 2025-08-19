package com.library.rbc.pagination.user;

import com.library.rbc.model.dto.RoleDto;
import com.library.rbc.model.dto.UserDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class UserPaginationSetUp {

  static final String USER_ID_1 = "1";
  static final String USER_ID_2 = "2";
  static final String USER_ID_3 = "3";
  static final String USER_FULL_NAME = "Full name";
  static final String USER_GOOGLE_ID = "2";
  static final String USER_EMAIL = "email";
  static final String USER_IMAGE_URL = "image";

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

  static UserDto createUserDto1() {
    return UserDto.builder()
        .id(USER_ID_1)
        .fullName(USER_FULL_NAME)
        .googleID(USER_GOOGLE_ID)
        .email(USER_EMAIL)
        .imageUrl(USER_IMAGE_URL)
        .role(RoleDto.EMPLOYEE)
        .build();
  }

  static UserDto createUserDto2() {
    UserDto userDto2 = createUserDto1();
    userDto2.setId(USER_ID_2);
    return userDto2;
  }

  static UserDto createUserDto3() {
    UserDto userDto3 = createUserDto1();
    userDto3.setId(USER_ID_3);
    return userDto3;
  }

  static Page<UserDto> createUserDtos() {
    UserDto userDto1 = createUserDto1();
    UserDto userDto2 = createUserDto2();
    UserDto userDto3 = createUserDto3();

    List<UserDto> userDtos = List.of(userDto1, userDto2, userDto3);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(userDtos, pageable, userDtos.size());
  }
}
