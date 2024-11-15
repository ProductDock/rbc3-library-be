package com.library.rbc.controller.bookcontroller;

import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.BOOK_CATEGORIES;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.BOOK_ID;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.BOOK_STATUS;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_NUMBER;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.PAGE_SIZE;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDto;
import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.BookController;
import com.library.rbc.exceptionhandler.BookNotFoundException;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.service.BookService;
import java.util.List;
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

    assertEquals(bookDto, result);
  }

  @Test
  void getResponseWhenNoBookIsFound() {
    when(bookService.getBook(BOOK_ID)).thenThrow(
        new BookNotFoundException("Book with ID " + BOOK_ID + " was not found."));

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookController.getBook(BOOK_ID);
    });

    String expectedMessage = "Book with ID " + BOOK_ID + " was not found.";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void addNewBook() {
    BookDto expected = createBookDto();

    when(bookService.addNewBook(expected)).thenReturn(expected);
    BookDto actual = bookController.addBook(expected).getBody();

    assertEquals(expected, actual);
  }

  @Test
  void returnBooksBy(){
    List<String> statuses = List.of("AVAILABLE");
    List<String> categories = List.of("MARKETING");
    Page<BookDto> expected = createBookDtos();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookService.getBooksBy(pageable,BOOK_CATEGORIES, BOOK_STATUS)).thenReturn(expected);
    Page<BookDto> result = bookController.returnBooksBy(pageable,categories,statuses);

    assertEquals(expected, result);
  }
}
