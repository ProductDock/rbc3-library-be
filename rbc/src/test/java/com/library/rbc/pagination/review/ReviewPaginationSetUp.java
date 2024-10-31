package com.library.rbc.pagination.review;

import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.SeniorityDto;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class ReviewPaginationSetUp {

  static final String REVIEW_ID = "1";
  static final Integer REVIEW_RATING = 5;
  static final String REVIEW_CONTENT = "Content";
  static final List<SeniorityDto> REVIEW_SENIORITY_DTOS = List.of();
  static final LocalDateTime REVIEW_DATE_TIME = LocalDateTime.now();
  static final String BOOK_ID = "1";
  static final String USER_ID = "1";

  static final String REVIEW_ID_2 = "2";
  static final String USER_ID_2 = "2";

  static final String REVIEW_ID_3 = "3";
  static final String USER_ID_3 = "3";

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 2;

  static ReviewDto createReviewDto1() {
    return ReviewDto.builder()
        .id(REVIEW_ID)
        .rating(REVIEW_RATING)
        .content(REVIEW_CONTENT)
        .seniorities(REVIEW_SENIORITY_DTOS)
        .dateTime(REVIEW_DATE_TIME)
        .bookId(BOOK_ID)
        .userId(USER_ID)
        .build();
  }

  static ReviewDto createReviewDto2() {
    ReviewDto reviewDto2 = createReviewDto1();
    reviewDto2.setId(REVIEW_ID_2);
    reviewDto2.setUserId(USER_ID_2);
    return reviewDto2;
  }

  static ReviewDto createReviewDto3() {
    ReviewDto reviewDto3 = createReviewDto1();
    reviewDto3.setId(REVIEW_ID_3);
    reviewDto3.setUserId(USER_ID_3);
    return reviewDto3;
  }

  static Page<ReviewDto> createReviewDtos() {
    ReviewDto reviewDto1 = createReviewDto1();
    ReviewDto reviewDto2 = createReviewDto2();
    ReviewDto reviewDto3 = createReviewDto3();

    List<ReviewDto> reviewDtos = List.of(reviewDto1, reviewDto2, reviewDto3);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(reviewDtos, pageable, reviewDtos.size());
  }
}
