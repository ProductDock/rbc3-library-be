package com.library.rbc.integration.bookcontroller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
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

  @Test
  public void shouldGetBookById() {
    Book book = BookSetUp.createBook1();
    bookRepository.save(book);
    webClient.get()
        .uri("/books/" + BookSetUp.BOOK_ID)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.id").isEqualTo(BookSetUp.BOOK_ID)
        .jsonPath("$.title").isEqualTo(BookSetUp.BOOK_TITLE)
        .jsonPath("$.authors[0].id").isEqualTo(BookSetUp.BOOK_AUTHOR.getId())
        .jsonPath("$.authors[0].fullName").isEqualTo(BookSetUp.BOOK_AUTHOR.getFullName())
        .jsonPath("$.imageUrl").isEqualTo(BookSetUp.BOOK_IMAGE_URL)
        .jsonPath("$.numberOfAvailableCopies")
        .isEqualTo(BookSetUp.BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .jsonPath("$.usersWhoFavourited").isEmpty();
  }

  @Test
  public void shouldCatchBookNotFoundException() {
    webClient.get()
        .uri("/books/" + BookSetUp.BOOK_ID)
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.message").isEqualTo("Book with ID " + BookSetUp.BOOK_ID + " was not found.");
  }

  @Test
  public void shouldAddNewBook() {
    BookDto bookDto = BookSetUp.createBookDto();

    webClient.post()
        .uri("/books")
        .bodyValue(bookDto)
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("$.title").isEqualTo(BookSetUp.BOOK_TITLE)
        .jsonPath("$.authors[0].id").isEqualTo(BookSetUp.BOOK_AUTHOR.getId())
        .jsonPath("$.authors[0].fullName").isEqualTo(BookSetUp.BOOK_AUTHOR.getFullName())
        .jsonPath("$.imageUrl").isEqualTo(BookSetUp.BOOK_IMAGE_URL)
        .jsonPath("$.numberOfAvailableCopies").isEqualTo(BookSetUp.BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .jsonPath("$.usersWhoFavourited").isEmpty();
  }

  @Test
  public void shouldFilterBook(){
    Book book1 = BookSetUp.createBook1();

    bookRepository.save(book1);

    webClient.get()
        .uri(uriBuilder ->
            uriBuilder.path("/books/filter")
                .queryParam("bookCategories", "MARKETING")
                .queryParam("bookStatuses", "AVAILABLE")
                .build())
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.content[0].id").isEqualTo(BookSetUp.BOOK_ID)
        .jsonPath("$.content[0].title").isEqualTo(BookSetUp.BOOK_TITLE)
        .jsonPath("$.content[0].authors[0].id").isEqualTo(BookSetUp.BOOK_AUTHOR.getId())
        .jsonPath("$.content[0].authors[0].fullName").isEqualTo(BookSetUp.BOOK_AUTHOR.getFullName())
        .jsonPath("$.content[0].imageUrl").isEqualTo(BookSetUp.BOOK_IMAGE_URL)
        .jsonPath("$.content[0].bookStatus").isEqualTo(BookSetUp.BOOK_STATUS)
        .jsonPath("$.content[0].bookCategories[0]").isEqualTo(BookSetUp.BOOK_CATEGORY)
        .jsonPath("$.content[0].numberOfAvailableCopies")
        .isEqualTo(BookSetUp.BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .jsonPath("$.content[0].usersWhoFavourited").isEmpty();
  }

  @Test
  public void shouldUploadImage() {
    byte[] imageBytes = "dummy image content".getBytes();

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("image", imageBytes).contentType(MediaType.IMAGE_JPEG).filename("test-image.jpg");

    webClient.post()
        .uri("/books/upload-image")
        .bodyValue(builder.build())
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.imagePath").isEqualTo(BookSetUp.IMAGE_PATH);
  }

  @Test
  public void shouldCatchContentTypeExceptionWhenNoContentType() {
    byte[] imageBytes = "dummy image content".getBytes();

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("image", imageBytes).filename("test-image.jpg");

    webClient.post()
        .uri("/books/upload-image")
        .bodyValue(builder.build())
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").isEqualTo("Provided file must be image");
  }


  @Test
  public void shouldCatchContentTypeExceptionWhenContentTypeNotImage() {
    byte[] imageBytes = "dummy image content".getBytes();

    MultipartBodyBuilder builder = new MultipartBodyBuilder();
    builder.part("image", imageBytes).contentType(MediaType.TEXT_HTML).filename("test-image.jpg");

    webClient.post()
        .uri("/books/upload-image")
        .bodyValue(builder.build())
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").isEqualTo("Provided file must be image");
  }
}
