package com.library.rbc.service;

import com.library.rbc.exceptionHandler.BookNotFoundException;
import com.library.rbc.model.dto.ReviewDto;
import com.library.rbc.model.dto.ReviewMapper;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final BookRepository bookRepository;
  private final ReviewMapper reviewMapper;

  public Page<ReviewDto> getReviewsByBookId(String bookId, Pageable pageable) {
    if (!bookRepository.existsById(bookId)) {
      throw new BookNotFoundException("There is no book with id: " + bookId);
    }
    return reviewRepository.findByBookId(bookId, pageable)
        .map(reviewMapper::reviewToReviewDto);
  }
}
