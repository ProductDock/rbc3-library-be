package com.library.rbc.controller;


import com.library.rbc.model.dto.UserDto;
import com.library.rbc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
    UserDto result = userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
