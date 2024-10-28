package com.library.rbc.model.dto;

import com.library.rbc.model.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

      ReviewDto reviewToReviewDto(Review review);

      Review reviewDtoToReview(ReviewDto reviewDto);

      List<ReviewDto> reviewsToReviewDtos(List<Review> reviews);

      List<Review> reviewDtosToReviews(List<ReviewDto> reviewDtos);
}
