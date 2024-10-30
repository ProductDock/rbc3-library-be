package com.library.rbc.service.reviewservice;

import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReview;
import static com.library.rbc.service.reviewservice.ReviewServiceSetUp.createReviewDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.model.Review;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.ReviewMapper;
import com.library.rbc.repository.ReviewRepository;
import com.library.rbc.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceShould {

  @InjectMocks
  private ReviewService reviewService;

  @Mock
  private ReviewRepository reviewRepository;

  @Mock
  private ReviewMapper reviewMapper;

  @Test
  void addNewReview() {
    Review review = createReview();
    ReviewDto expected = createReviewDto();

    when(reviewMapper.reviewDtoToReview(expected)).thenReturn(review);
    when(reviewRepository.save(review)).thenReturn(review);
    ReviewDto actual = reviewService.addReview(expected);

    assertEquals(expected, actual);
  }
}
