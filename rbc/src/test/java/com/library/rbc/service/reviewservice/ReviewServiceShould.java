package com.library.rbc.service.reviewservice;

import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.BOOK_ID;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.PAGE_NUMBER;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.PAGE_SIZE;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReview;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewDto;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewDtosPage;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewsPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

//  @Test
//  void catchExceptionIfBookDoesntExist() {
//    when(bookRepository.existsById(BOOK_ID)).thenReturn(true);
//
//  }
}
