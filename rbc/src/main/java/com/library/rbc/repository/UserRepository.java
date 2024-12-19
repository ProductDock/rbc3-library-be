package com.library.rbc.repository;

import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

  UserDto findByEmail(String email);
}
