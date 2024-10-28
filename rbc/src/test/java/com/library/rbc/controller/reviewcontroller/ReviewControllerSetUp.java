package com.library.rbc.controller.reviewcontroller;

import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.SeniorityDto;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewControllerSetUp {

    static final String REVIEW_ID = "1";
    static final Integer REVIEW_RATING = 5;
    static final String REVIEW_CONTENT = "Content";
    static final List<SeniorityDto> REVIEW_SENIORITY_DTOS = List.of();
    static final LocalDateTime REVIEW_DATE_TIME = LocalDateTime.now();
    static final String BOOK_ID = "1";
    static final String USER_ID = "1";

    static ReviewDto createReviewDto() {
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
}
