package com.library.rbc.integration.reviewcontroller;

import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.BOOK_ID;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.REVIEW_CONTENT;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.REVIEW_DATE_TIME;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.REVIEW_ID;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.REVIEW_ID_2;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.REVIEW_RATING;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.USER_ID;
import static com.library.rbc.integration.reviewcontroller.ReviewSetUp.USER_ID_2;
import static org.hamcrest.Matchers.startsWith;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.library.rbc.model.Book;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.Review;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.repository.ReviewRepository;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ReviewIntegrationTest {

  @Autowired
  private WebTestClient webClient;

  @Autowired
  private ReviewRepository reviewRepository;

  @Autowired
  private BookRepository bookRepository;

  @BeforeEach
  public void setUp() {
    bookRepository.deleteAll();
    reviewRepository.deleteAll();
  }

  @Test
  public void shouldGetReviewsByBookId() {
    Book book = ReviewSetUp.createBook();
    Review review1 = ReviewSetUp.createReview1();
    Review review2 = ReviewSetUp.createReview2();

    bookRepository.save(book);
    reviewRepository.save(review1);
    reviewRepository.save(review2);

    webClient.get()
        .uri("/books/" + BOOK_ID + "/reviews")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.content[0].id").isEqualTo(REVIEW_ID)
        .jsonPath("$.content[1].id").isEqualTo(REVIEW_ID_2)
        .jsonPath("$.content[0].rating").isEqualTo(REVIEW_RATING)
        .jsonPath("$.content[0].content").isEqualTo(REVIEW_CONTENT)
        .jsonPath("$.content[0].seniorities").isEmpty()
        .jsonPath("$.content[0].dateTime")
        .value(startsWith(REVIEW_DATE_TIME.truncatedTo(ChronoUnit.SECONDS).toString()))
        .jsonPath("$.content[0].bookId").isEqualTo(BOOK_ID)
        .jsonPath("$.content[1].bookId").isEqualTo(BOOK_ID)
        .jsonPath("$.content[0].userId").isEqualTo(USER_ID)
        .jsonPath("$.content[1].userId").isEqualTo(USER_ID_2)
        .jsonPath("$.content.size()").isEqualTo(2);
  }

  @Test
  public void shouldCatchBookNotFoundException() {
    Review review1 = ReviewSetUp.createReview1();
    Review review2 = ReviewSetUp.createReview2();

    reviewRepository.save(review1);
    reviewRepository.save(review2);

    webClient.get()
        .uri("/books/" + BOOK_ID + "/reviews")
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.message").isEqualTo("Book with ID " + BOOK_ID + " was not found.");
  }

  @Test
  public void shouldAddNewReview() {
    ReviewDto review = ReviewSetUp.createReviewDto();

    webClient.post()
        .uri("/books/" + ReviewSetUp.BOOK_ID + "/reviews")
        .bodyValue(review)
        .exchange()
        .expectStatus().isCreated()
        .expectBody()
        .jsonPath("$.id").isEqualTo(ReviewSetUp.REVIEW_ID)
        .jsonPath("$.rating").isEqualTo(ReviewSetUp.REVIEW_RATING)
        .jsonPath("$.content").isEqualTo(ReviewSetUp.REVIEW_CONTENT)
        .jsonPath("$.seniorities[0]").isEqualTo("JUNIOR")
        .jsonPath("$.seniorities[1]").isEqualTo("MEDIOR")
        .jsonPath("$.dateTime").isEqualTo(ReviewSetUp.REVIEW_DATE_TIME.toString())
        .jsonPath("$.bookId").isEqualTo(ReviewSetUp.BOOK_ID)
        .jsonPath("$.userId").isEqualTo(ReviewSetUp.USER_ID);
  }
}
