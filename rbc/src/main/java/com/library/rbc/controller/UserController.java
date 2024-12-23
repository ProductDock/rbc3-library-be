package com.library.rbc.controller;


import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public Page<UserDto> getAllUsers(
      @ParameterObject @PageableDefault(size = 12) Pageable pageable) {
    return userService.getAllUsers(pageable);
  }

  @PostMapping("/login")
  public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
    UserDto result = userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @PatchMapping("/{userId}/updateRole")
  public User updateRole(@PathVariable String userId) {
    return userService.updateRole(userId);
  }

  @GetMapping("/{googleID}")
  public UserDto getUser(@PathVariable String googleID) {
    return userService.getUser(googleID);
  }
}
