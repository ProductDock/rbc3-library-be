package com.library.rbc.controller.bookcontroller;

import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.BOOK_ID;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDto;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDtos;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createNotFoundResponseEntity;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.BookController;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BookControllerShould {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Test
  void getAllBooks() {
    Page<BookDto> bookDtos = createBookDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookService.getAllBooks(pageable)).thenReturn(bookDtos);
    Page<BookDto> result = bookController.getAllBooks(pageable);

    Page<BookDto> expected = createBookDtos();
    assertEquals(expected, result);
  }

  @Test
  void getBook() {
    BookDto bookDto = createBookDto();

    when(bookService.getBook(BOOK_ID)).thenReturn(bookDto);
    ResponseEntity<BookDto> result = bookController.getBook(BOOK_ID);

    ResponseEntity<BookDto> expected = createResponseEntity();
    assertEquals(expected, result);
  }

  @Test
  void getResponseWhenNoBookIsFound() {
    when(bookService.getBook(BOOK_ID)).thenReturn(null);
    ResponseEntity<BookDto> result = bookController.getBook(BOOK_ID);

    ResponseEntity<BookDto> expected = createNotFoundResponseEntity();
    assertEquals(expected, result);
  }
}
