package com.library.rbc.controller.usercontroller;

import static com.library.rbc.controller.usercontroller.UserControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.usercontroller.UserControllerSetUp.createUserDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.UserController;
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
    Page<UserDto> userDtos = createUserDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(userService.getAllUsers(pageable)).thenReturn(userDtos);

    Page<UserDto> result = userController.getAllUsers(pageable);

    Page<UserDto> expected = createUserDtos();
    assertEquals(userDtos, result);
  }

}
