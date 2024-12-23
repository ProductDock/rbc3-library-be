package com.library.rbc.controller.usercontroller;

import static com.library.rbc.controller.usercontroller.UserControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.USER_EMAIL;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.USER_GOOGLE_ID;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.USER_ID;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.createUser2;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.createUserDto;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.createUserDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.UserController;
import com.library.rbc.exceptionhandler.EmailAlreadyExistsException;
import com.library.rbc.exceptionhandler.UserNotFoundException;
import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class UserControllerShould {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  @Test
  void getAllUsers() {
    Page<UserDto> expected = createUserDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(userService.getAllUsers(pageable)).thenReturn(expected);

    Page<UserDto> actual = userController.getAllUsers(pageable);

    assertEquals(expected, actual);
  }

  @Test
  void saveNewUser() {
    UserDto expected = createUserDto();

    when(userService.saveUser(expected)).thenReturn(expected);

    UserDto actual = userController.saveUser(expected).getBody();

    assertEquals(expected, actual);
  }

  @Test
  void getResponseWhenUserWithSameEmailAlreadyExists() {
    UserDto userDto = createUserDto();

    when(userService.saveUser(userDto)).thenThrow(
        new EmailAlreadyExistsException("User with this email already exists: " + USER_EMAIL));

    EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, () ->
        userController.saveUser(userDto));

    String expectedMessage = "User with this email already exists: " + USER_EMAIL;
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void updateRole() {
    User updatedUser = createUser2();

    when(userService.updateRole(USER_ID)).thenReturn(updatedUser);

    User actualUser = userController.updateRole(USER_ID);

    assertEquals(updatedUser.getRole(), actualUser.getRole());
  }

  @Test
  void getResponseWhenNoUserIsFound() {
    when(userService.updateRole(USER_ID)).thenThrow(
        new UserNotFoundException("User with id " + USER_ID + " not found"));

    UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
      userController.updateRole(USER_ID);
    });

    String expectedMessage = "User with id " + USER_ID + " not found";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void getUserByGoogleId() {
    UserDto expected = createUserDto();

    when(userService.getUser(USER_GOOGLE_ID)).thenReturn(expected);

    UserDto actual = userController.getUser(USER_GOOGLE_ID);

    assertEquals(expected, actual);
  }

  @Test
  void getResponseWhenUserWithGoogleIdIsNotFound() {
    when(userService.getUser(USER_GOOGLE_ID)).thenThrow(
        new UserNotFoundException("User with provided google id does not exist"));

    UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
      userController.getUser(USER_GOOGLE_ID);
    });

    String expectedMessage = "User with provided google id does not exist";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

}
