package com.library.rbc.integration.reviewcontroller;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.repository.ReviewRepository;
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

  @BeforeEach
  public void setUp() {
    reviewRepository.deleteAll();
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
