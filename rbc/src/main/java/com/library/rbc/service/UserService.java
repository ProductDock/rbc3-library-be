package com.library.rbc.service;

import com.library.rbc.exceptionhandler.EmailAlreadyExistsException;
import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.dto.UserMapper;
import com.library.rbc.model.enums.Role;
import com.library.rbc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;


  public UserDto saveUser(UserDto userDto) {
    UserDto existingUser = userRepository.findByEmail(userDto.getEmail());
    if (existingUser != null) {
      throw new EmailAlreadyExistsException(
          "User with this email already exists: " + userDto.getEmail());
    }
    userDto.setRole(Role.EMPLOYEE);
    User newUser = userMapper.userDtoToUser(userDto);
    User savedUser = userRepository.save(newUser);
    return userMapper.userToUserDto(savedUser);
  }

}
