package com.library.rbc.model.dto;

import com.library.rbc.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

  ReviewDto reviewToReviewDto(Review review);

  Review reviewDtoToReview(ReviewDto reviewDto);
}
