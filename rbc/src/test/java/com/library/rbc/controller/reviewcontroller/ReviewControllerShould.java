package com.library.rbc.controller.reviewcontroller;

import com.library.rbc.controller.ReviewController;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.library.rbc.controller.reviewcontroller.ReviewControllerSetUp.createReviewDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerShould {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private ReviewService reviewService;

    @Test
    void addReview() {
        ReviewDto expected = createReviewDto();

        when(reviewService.addReview(expected)).thenReturn(expected);
        ReviewDto actual = reviewController.addReview(expected).getBody();

        assertEquals(expected, actual);
    }
}
