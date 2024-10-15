package com.library.rbc.pagination.book;

import static com.library.rbc.pagination.book.BookPaginationSetUp.PAGE_NUMBER;
import static com.library.rbc.pagination.book.BookPaginationSetUp.PAGE_SIZE;
import static com.library.rbc.pagination.book.BookPaginationSetUp.createBookDtos;
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
public class BookPaginationShould {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Test
  void getBookPage() {
    Page<BookDto> bookDtos = createBookDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookService.getAllBooks(pageable)).thenReturn(bookDtos);
    Page<?> result = bookController.getAllBooks(PAGE_NUMBER, PAGE_SIZE);

    Page<?> expected = createBookDtos();
    assertEquals(expected, result);
    assertEquals(PAGE_SIZE, result.getSize());
  }
}
