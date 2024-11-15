package com.library.rbc.controller;

import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books/{bookId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping
  public Page<ReviewDto> getReviewsByBookId(@PathVariable String bookId,
      @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
    return reviewService.getReviewsByBookId(bookId, pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
    return reviewService.addReview(reviewDto);
  }
}
