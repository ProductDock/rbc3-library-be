package com.library.rbc.controller;

import com.library.rbc.model.dto.BookDto;
import com.library.rbc.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @GetMapping
  public Page<BookDto> getAllBooks(
      @ParameterObject @PageableDefault(size = 12) Pageable pageable) {
    return bookService.getAllBooks(pageable);
  }
}
