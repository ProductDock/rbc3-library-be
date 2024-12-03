package com.library.rbc.service.reviewservice;

import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.BOOK_ID;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.PAGE_NUMBER;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.PAGE_SIZE;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReview;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewDto;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewDtosPage;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.rbc.exceptionhandler.BookNotFoundException;
import com.library.rbc.model.Review;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.ReviewMapper;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.repository.ReviewRepository;
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
public class ReviewServiceShould {

  @InjectMocks
  private ReviewService reviewService;

  @Mock
  private ReviewRepository reviewRepository;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private ReviewMapper reviewMapper;

  @Test
  void getReviewsByBookId() {
    Review review = createReview();
    ReviewDto reviewDto = createReviewDto();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookRepository.existsById(BOOK_ID)).thenReturn(true);
    when(reviewRepository.findByBookId(BOOK_ID, pageable)).thenReturn(createReviewsPage());
    when(reviewMapper.reviewToReviewDto(review)).thenReturn(reviewDto);
    Page<ReviewDto> result = reviewService.getReviewsByBookId(BOOK_ID, pageable);

    Page<ReviewDto> expected = createReviewDtosPage();
    assertEquals(expected, result);
  }

  @Test
  void catchExceptionIfBookDoesntExist() {
    Pageable pageable = PageRequest.of(
        PAGE_NUMBER, PAGE_SIZE);

    when(bookRepository.existsById(BOOK_ID)).thenReturn(false);

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      reviewService.getReviewsByBookId(BOOK_ID, pageable);
    });

    String expectedMessage = "Book with ID " + BOOK_ID + " was not found.";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    verify(reviewMapper, never()).reviewToReviewDto(any());
  }

  @Test
  void addNewReview() {
    Review review = createReview();
    ReviewDto expected = createReviewDto();

    when(reviewMapper.reviewDtoToReview(expected)).thenReturn(review);
    when(reviewRepository.save(review)).thenReturn(review);
    when(reviewMapper.reviewToReviewDto(review)).thenReturn(expected);

    ReviewDto actual = reviewService.addReview(expected);

    assertEquals(expected, actual);
  }
  
}
