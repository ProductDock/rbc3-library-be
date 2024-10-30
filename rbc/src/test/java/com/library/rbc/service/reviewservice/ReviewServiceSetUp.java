package com.library.rbc.service.reviewservice;

import com.library.rbc.model.Review;
import com.library.rbc.model.dto.ReviewDto;

public class ReviewServiceSetUp {

  static final String REVIEW_ID = "1";
  static final String REVIEW_CONTENT = "Great book!";
  static final int REVIEW_RATING = 5;
  static final String REVIEW_USER_ID = "1";
  static final String REVIEW_BOOK_ID = "1";

  static Review createReview() {
    return Review.builder()
        .id(REVIEW_ID)
        .rating(REVIEW_RATING)
        .content(REVIEW_CONTENT)
        .userId(REVIEW_USER_ID)
        .bookId(REVIEW_BOOK_ID)
        .build();
  }

  static ReviewDto createReviewDto() {
    return ReviewDto.builder()
        .id(REVIEW_ID)
        .content(REVIEW_CONTENT)
        .rating(REVIEW_RATING)
        .userId(REVIEW_USER_ID)
        .bookId(REVIEW_BOOK_ID)
        .build();
  }
}
