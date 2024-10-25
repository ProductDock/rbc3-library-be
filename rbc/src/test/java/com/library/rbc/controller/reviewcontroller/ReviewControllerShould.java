package com.library.rbc.controller.reviewcontroller;

import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.BOOK_ID;
import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.createReviewDtosPage;
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
public class ReviewControllerShould {

  @InjectMocks
  private ReviewController reviewController;

  @Mock
  private ReviewService reviewService;

  @Test
  void getReviewsByBookId() {
    Page<ReviewDto> reviewDtos = ReviewControllerSetUp.createReviewDtosPage();
    Pageable pageable = PageRequest.of(ReviewControllerSetUp.PAGE_NUMBER,
        ReviewControllerSetUp.PAGE_SIZE);

    when(reviewService.getReviewsByBookId(BOOK_ID, pageable)).thenReturn(reviewDtos);
    Page<ReviewDto> result = reviewController.getReviewsByBookId(BOOK_ID, pageable);

    Page<ReviewDto> expected = createReviewDtosPage();
    assertEquals(expected, result);
  }
}
