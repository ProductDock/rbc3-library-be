package com.library.rbc.service;

import com.library.rbc.exceptionhandler.EmailAlreadyExistsException;
import com.library.rbc.exceptionhandler.UserNotFoundException;
import com.library.rbc.model.User;
import com.library.rbc.model.dto.RoleDto;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.dto.UserMapper;
import com.library.rbc.model.enums.Role;
import com.library.rbc.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;


  public Page<UserDto> getAllUsers(Pageable pageable) {
    return userRepository.findAll(pageable).map(userMapper::userToUserDto);
  }

  public UserDto saveUser(UserDto userDto) {
    UserDto existingUser = userRepository.findByEmail(userDto.getEmail());
    if (existingUser != null) {
      throw new EmailAlreadyExistsException(
          "User with this email already exists: " + userDto.getEmail());
    }
    userDto.setRole(RoleDto.EMPLOYEE);
    User newUser = userMapper.userDtoToUser(userDto);
    User savedUser = userRepository.save(newUser);
    return userMapper.userToUserDto(savedUser);
  }

  public User updateRole(String id) {
    Optional<User> optionalUser = userRepository.findById(id);
    return optionalUser.map(user -> {
      user.setRole(Role.ADMIN);
      userRepository.save(user);
      return user;
    }).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
  }
}
