package com.library.rbc.service.userservice;

import static com.library.rbc.service.userservice.UserServiceSetUp.PAGE_NUMBER;
import static com.library.rbc.service.userservice.UserServiceSetUp.PAGE_SIZE;
import static com.library.rbc.service.userservice.UserServiceSetUp.USER_EMAIL;
import static com.library.rbc.service.userservice.UserServiceSetUp.USER_ID;
import static com.library.rbc.service.userservice.UserServiceSetUp.createUser;
import static com.library.rbc.service.userservice.UserServiceSetUp.createUserDto;
import static com.library.rbc.service.userservice.UserServiceSetUp.createUserDtosPage;
import static com.library.rbc.service.userservice.UserServiceSetUp.createUserPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.library.rbc.exceptionhandler.EmailAlreadyExistsException;
import com.library.rbc.exceptionhandler.UserNotFoundException;
import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.dto.UserMapper;
import com.library.rbc.model.enums.Role;
import com.library.rbc.repository.UserRepository;
import com.library.rbc.service.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class UserServiceShould {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private UserMapper userMapper;

  @Test
  void getAllUsers() {
    User user = createUser();
    UserDto userDto = createUserDto();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    Page<UserDto> expectedBookDtos = createUserDtosPage();

    when(userRepository.findAll(pageable)).thenReturn(createUserPage());
    when(userMapper.userToUserDto(user)).thenReturn(userDto);

    Page<UserDto> result = userService.getAllUsers(pageable);

    assertEquals(expectedBookDtos, result);
  }

  @Test
  void saveUser() {
    User user = createUser();
    UserDto expected = createUserDto();

    when(userRepository.findByEmail(USER_EMAIL)).thenReturn(null);
    when(userMapper.userDtoToUser(expected)).thenReturn(user);
    when(userRepository.save(user)).thenReturn(user);
    when(userMapper.userToUserDto(user)).thenReturn(expected);

    UserDto actual = userService.saveUser(expected);

    assertEquals(expected, actual);
  }

  @Test
  void throwEmailAlreadyExistsException() {
    UserDto userDto = createUserDto();
    String expectedMessage = "User with this email already exists: " + USER_EMAIL;

    when(userRepository.findByEmail(USER_EMAIL)).thenReturn(userDto);
    EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () ->
        userService.saveUser(userDto));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void updateRole() {
    User expected = createUser();

    when(userRepository.findById(USER_ID)).thenReturn(Optional.of(expected));
    expected.setRole(Role.ADMIN);
    when(userRepository.save(expected)).thenReturn(expected);

    Optional<User> actual = userService.updateRole(USER_ID);

    assertEquals(Optional.of(expected), actual);
  }

  @Test
  void throwUserNotFoundException() {
    String expectedMessage = "User with id " + USER_ID + " not found";

    when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
    UserNotFoundException exception = assertThrows(UserNotFoundException.class, () ->
        userService.updateRole(USER_ID));

    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }
}
