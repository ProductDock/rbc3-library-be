package com.library.rbc.controller.bookcontroller;

import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.BOOK_ID;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDto;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.BookController;
import com.library.rbc.exceptionHandler.BookNotFoundException;
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
    BookDto result = bookController.getBook(BOOK_ID);

    BookDto expected = createBookDto();
    assertEquals(expected, result);
  }

  @Test
  void getResponseWhenNoBookIsFound() {
    when(bookService.getBook(BOOK_ID)).thenThrow(
        new BookNotFoundException("There is no book with id: " + BOOK_ID));
    
    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookController.getBook(BOOK_ID);
    });

    String expectedMessage = "There is no book with id: " + BOOK_ID;
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }
}
