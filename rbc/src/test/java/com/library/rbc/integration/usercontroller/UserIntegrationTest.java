package com.library.rbc.integration.usercontroller;

import static com.library.rbc.integration.usercontroller.UserSetUp.USER_2_ID;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_2_ROLE;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_EMAIL;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_FULL_NAME;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_ID;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_IMAGE_URL;
import static com.library.rbc.integration.usercontroller.UserSetUp.USER_ROLE;
import static com.library.rbc.integration.usercontroller.UserSetUp.createUser;
import static com.library.rbc.integration.usercontroller.UserSetUp.createUser2;
import static com.library.rbc.integration.usercontroller.UserSetUp.createUserDto;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.library.rbc.model.User;
import com.library.rbc.model.dto.UserDto;
import com.library.rbc.model.enums.Role;
import com.library.rbc.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class UserIntegrationTest {

  @Autowired
  private WebTestClient webClient;

  @Autowired
  private UserRepository userRepository;

  @BeforeEach
  public void setUp() {
    userRepository.deleteAll();
  }

  @Test
  public void shouldGetAllUsers() {
    User user1 = createUser();
    User user2 = createUser2();

    userRepository.save(user1);
    userRepository.save(user2);

    webClient.get()
        .uri("/users")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.content[0].id").isEqualTo(USER_ID)
        .jsonPath("$.content[1].id").isEqualTo(USER_2_ID)
        .jsonPath("$.content[0].fullName").isEqualTo(USER_FULL_NAME)
        .jsonPath("$.content[0].email").isEqualTo(USER_EMAIL)
        .jsonPath("$.content[0].imageUrl").isEqualTo(USER_IMAGE_URL)
        .jsonPath("$.content[0].role").isEqualTo(USER_ROLE)
        .jsonPath("$.content[1].role").isEqualTo(USER_2_ROLE)
        .jsonPath("$.content.size()").isEqualTo(2);
  }

  @Test
  public void shouldAddNewUser() {
    UserDto userDto = createUserDto();

    webClient.post()
        .uri("/users/login")
        .bodyValue(userDto)
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("$.fullName").isEqualTo(USER_FULL_NAME)
        .jsonPath("$.email").isEqualTo(USER_EMAIL)
        .jsonPath("$.imageUrl").isEqualTo(USER_IMAGE_URL)
        .jsonPath("$.role").isEqualTo(USER_ROLE);
  }

  @Test
  public void shouldCatchEmailAlreadyExistsException() {
    User user = createUser();
    User userDto2 = createUser2();

    userRepository.save(user);

    webClient.post()
        .uri("/users/login")
        .bodyValue(userDto2)
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").isEqualTo("User with this email already exists: " + USER_EMAIL);
  }

  @Test
  public void shouldUpdateUserRole() {
    User user = createUser();
    
    user.setRole(Role.ADMIN);
    userRepository.save(user);

    webClient.patch()
        .uri("/users/updateRole/{userId}", user.getId())
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.role").isEqualTo(USER_2_ROLE);

  }

  @Test
  public void shouldCatchUserNotFoundException() {
    User user = createUser();

    webClient.patch()
        .uri("/users/updateRole/{userId}", user.getId())
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.message").isEqualTo("User with id " + user.getId() + " not found");
  }
}
