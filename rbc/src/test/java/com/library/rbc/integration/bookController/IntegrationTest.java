package com.library.rbc.integration.bookController;

import com.library.rbc.model.Book;
import com.library.rbc.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

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
                .jsonPath("$[0].id").isEqualTo(BookSetUp.BOOK_ID)
                .jsonPath("$[1].id").isEqualTo(BookSetUp.BOOK_2_ID)
                .jsonPath("$[0].title").isEqualTo(BookSetUp.BOOK_TITLE)
                .jsonPath("$[0].authors[0].id").isEqualTo(BookSetUp.BOOK_AUTHOR.getId())
                .jsonPath("$[0].authors[0].fullName").isEqualTo(BookSetUp.BOOK_AUTHOR.getFullName())
                .jsonPath("$[0].imageUrl").isEqualTo(BookSetUp.BOOK_IMAGE_URL)
                .jsonPath("$[0].numberOfAvailableCopies").isEqualTo(BookSetUp.BOOK_NUMBER_OF_AVAILABLE_COPIES)
                .jsonPath("$[0].usersWhoFavourited").isEmpty()
                .jsonPath("$", hasSize(1));
    }
}
