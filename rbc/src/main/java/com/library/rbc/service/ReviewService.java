package com.library.rbc.service;

import com.library.rbc.model.Review;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.ReviewMapper;
import com.library.rbc.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewDto addReview(ReviewDto reviewDto) {
        Review review = reviewMapper.reviewDtoToReview(reviewDto);
        reviewRepository.save(review);
        return reviewDto;
    }
}
