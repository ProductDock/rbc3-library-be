package com.library.rbc.service.userservice;

import com.library.rbc.model.User;
import com.library.rbc.model.dto.RoleDto;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.enums.Role;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class UserServiceSetUp {

  static final String USER_ID = "1";
  static final String USER_FULL_NAME = "Full name";
  static final String USER_GOOGLE_ID = "2";
  static final String USER_EMAIL = "email";
  static final String USER_IMAGE_URL = "image";

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

  static User createUser() {
    return User.builder()
        .id(USER_ID)
        .fullName(USER_FULL_NAME)
        .googleID(USER_GOOGLE_ID)
        .email(USER_EMAIL)
        .imageUrl(USER_IMAGE_URL)
        .role(Role.EMPLOYEE)
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

  static List<User> createUsers() {
    User user = createUser();
    return List.of(user);
  }

  static Page<UserDto> createUserDtosPage() {
    UserDto userDto = createUserDto();
    List<UserDto> userDtos = List.of(userDto);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(userDtos, pageable, userDtos.size());
  }

  static Page<User> createUserPage() {
    List<User> users = createUsers();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(users, pageable, users.size());
  }

}
