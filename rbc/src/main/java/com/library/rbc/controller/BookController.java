package com.library.rbc.controller;

import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.ImageDto;
import com.library.rbc.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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

  @GetMapping("/{id}")
  public BookDto getBook(@PathVariable String id) {
    return bookService.getBook(id);
  }

  @PostMapping
  public ResponseEntity<BookDto> addBook(@RequestBody BookDto book) {
    BookDto result = bookService.addNewBook(book);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/filter")
  public Page<BookDto> returnBooksBy(@ParameterObject @PageableDefault(size = 12) Pageable pageable,
      @RequestParam(required = false) List<String> bookCategories,
      @RequestParam(required = false) List<String> bookStatuses) {
    return bookService.getBooksBy(pageable, bookCategories, bookStatuses);
  }

  @PostMapping("/upload-image")
  public ResponseEntity<ImageDto> uploadImage(@RequestPart MultipartFile image) {
    String result = bookService.uploadImage(image);
    return ResponseEntity.status(HttpStatus.OK).body(new ImageDto(result));
  }
}
