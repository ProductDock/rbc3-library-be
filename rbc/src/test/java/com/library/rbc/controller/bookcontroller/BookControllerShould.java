package com.library.rbc.controller.bookcontroller;

import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDtos;
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

@ExtendWith(MockitoExtension.class)
public class BookControllerShould {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Test
  void getAllBooks() {
    Page<BookDto> bookDtos = createBookDtos();
    Pageable pageable = PageRequest.of(0, 10);

    when(bookService.getAllBooks(pageable)).thenReturn(bookDtos);
    Page<BookDto> result = bookController.getAllBooks(0, 10);

    Page<BookDto> expected = createBookDtos();
    assertEquals(expected, result);
  }
}
