package com.library.rbc.model.dto;

import com.library.rbc.model.User;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


  UserDto userToUserDto(User user);

  User userDtoToUser(UserDto userDto);

  List<UserDto> usersToUsersDto(List<User> users);

  List<User> userDtosToUsers(List<UserDto> userDtos);

}
