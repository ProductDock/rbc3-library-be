package com.library.rbc.service.reviewservice;

import com.library.rbc.model.Review;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.SeniorityDto;
import com.library.rbc.model.enums.Seniority;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ReviewServiceSetUp {

  static final String REVIEW_ID = "1";
  static final Integer REVIEW_RATING = 5;
  static final String REVIEW_CONTENT = "Great book!";
  static final List<Seniority> REVIEW_SENIORITIES = List.of();
  static final List<SeniorityDto> REVIEW_SENIORITY_DTOS = List.of();
  static final LocalDateTime REVIEW_DATE_TIME = LocalDateTime.now();
  static final String REVIEW_USER_ID = "1";
  static final String REVIEW_BOOK_ID = "1";
  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

  static Review createReview() {
    return Review.builder()
        .id(REVIEW_ID)
        .rating(REVIEW_RATING)
        .content(REVIEW_CONTENT)
        .seniorities(REVIEW_SENIORITIES)
        .dateTime(REVIEW_DATE_TIME)
        .userId(REVIEW_USER_ID)
        .bookId(REVIEW_BOOK_ID)
        .build();
  }

  static ReviewDto createReviewDto() {
    return ReviewDto.builder()
        .id(REVIEW_ID)
        .rating(REVIEW_RATING)
        .content(REVIEW_CONTENT)
        .seniorities(REVIEW_SENIORITY_DTOS)
        .dateTime(REVIEW_DATE_TIME)
        .userId(REVIEW_USER_ID)
        .bookId(REVIEW_BOOK_ID)
        .build();
  }

  static List<Review> createReviews() {
    Review review = createReview();
    return List.of(review);
  }

  static List<ReviewDto> createReviewDtos() {
    ReviewDto reviewDto = createReviewDto();
    return List.of(reviewDto);
  }

  static Page<Review> createReviewsPage() {
    List<Review> reviews = createReviews();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(reviews, pageable, reviews.size());
  }

  static Page<ReviewDto> createReviewDtosPage() {
    List<ReviewDto> reviewDtos = createReviewDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(reviewDtos, pageable, reviewDtos.size());
  }

}
