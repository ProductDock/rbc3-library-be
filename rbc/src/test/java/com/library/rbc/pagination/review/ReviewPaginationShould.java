package com.library.rbc.pagination.review;

import static com.library.rbc.pagination.review.ReviewPaginationSetUp.BOOK_ID;
import static com.library.rbc.pagination.review.ReviewPaginationSetUp.PAGE_NUMBER;
import static com.library.rbc.pagination.review.ReviewPaginationSetUp.PAGE_SIZE;
import static com.library.rbc.pagination.review.ReviewPaginationSetUp.createReviewDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.ReviewController;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class ReviewPaginationShould {

  @InjectMocks
  private ReviewController reviewController;

  @Mock
  private ReviewService reviewService;

  @Test
  void getReviewPage() {
    Page<ReviewDto> reviewDtos = createReviewDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(reviewService.getReviewsByBookId(BOOK_ID, pageable)).thenReturn(reviewDtos);
    Page<?> result = reviewController.getReviewsByBookId(BOOK_ID, pageable);

    Page<?> expected = createReviewDtos();
    assertEquals(expected, result);
    assertEquals(PAGE_SIZE, result.getSize());
  }
}
