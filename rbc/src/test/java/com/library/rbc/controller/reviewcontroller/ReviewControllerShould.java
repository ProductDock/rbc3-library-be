package com.library.rbc.controller.reviewcontroller;

import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.BOOK_ID;
import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.createReviewDtosPage;
import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.createReviewDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.ReviewController;
import com.library.rbc.exceptionhandler.BookNotFoundException;
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
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(reviewService.getReviewsByBookId(BOOK_ID, pageable)).thenReturn(reviewDtos);
    Page<ReviewDto> result = reviewController.getReviewsByBookId(BOOK_ID, pageable);

    Page<ReviewDto> expected = createReviewDtosPage();
    assertEquals(expected, result);
  }

  @Test
  void getResponseWhenNoBookIsFound() {
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    when(reviewService.getReviewsByBookId(BOOK_ID, pageable)).thenThrow(
        new BookNotFoundException("Book with ID " + BOOK_ID + " was not found."));

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      reviewController.getReviewsByBookId(BOOK_ID, pageable);
    });

    String expectedMessage = "Book with ID " + BOOK_ID + " was not found.";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void addReview() {
    ReviewDto expected = createReviewDto();

    when(reviewService.addReview(expected)).thenReturn(expected);
    ReviewDto actual = reviewController.addReview(expected);

    assertEquals(expected, actual);
  }

}
