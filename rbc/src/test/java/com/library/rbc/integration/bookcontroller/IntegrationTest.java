package com.library.rbc.integration.bookcontroller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.library.rbc.model.Book;
import com.library.rbc.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IntegrationTest {

  @Autowired
  private WebTestClient webClient;

  @Autowired
  private BookRepository bookRepository;

  @BeforeEach
  public void setUp() {
    bookRepository.deleteAll();
  }

  @Test
  public void shouldGetAllBooks() {
    Book book1 = BookSetUp.createBook1();
    Book book2 = BookSetUp.createBook2();

    bookRepository.save(book1);
    bookRepository.save(book2);

    webClient.get()
        .uri("/books")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.content[0].id").isEqualTo(BookSetUp.BOOK_ID)
        .jsonPath("$.content[1].id").isEqualTo(BookSetUp.BOOK_2_ID)
        .jsonPath("$.content[0].title").isEqualTo(BookSetUp.BOOK_TITLE)
        .jsonPath("$.content[0].authors[0].id").isEqualTo(BookSetUp.BOOK_AUTHOR.getId())
        .jsonPath("$.content[0].authors[0].fullName").isEqualTo(BookSetUp.BOOK_AUTHOR.getFullName())
        .jsonPath("$.content[0].imageUrl").isEqualTo(BookSetUp.BOOK_IMAGE_URL)
        .jsonPath("$.content[0].numberOfAvailableCopies")
        .isEqualTo(BookSetUp.BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .jsonPath("$.content[0].usersWhoFavourited").isEmpty()
        .jsonPath("$.content.size()").isEqualTo(2);
  }
}
