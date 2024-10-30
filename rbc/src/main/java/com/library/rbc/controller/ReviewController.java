package com.library.rbc.controller;

import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books/{bookId}/reviews")
@RequiredArgsConstructor
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<ReviewDto> addReview(@RequestBody ReviewDto reviewDto) {
    ReviewDto result = reviewService.addReview(reviewDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }
}
