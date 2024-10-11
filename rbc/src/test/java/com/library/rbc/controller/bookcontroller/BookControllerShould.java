package com.library.rbc.controller.bookcontroller;

import static com.library.rbc.controller.bookcontroller.BookControllerSetUp.createBookDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.controller.BookController;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.service.BookService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookControllerShould {

  @InjectMocks
  private BookController bookController;

  @Mock
  private BookService bookService;

  @Test
  void getAllBooks() {
    List<BookDto> bookDtos = createBookDtos();
    when(bookService.getAllBooks()).thenReturn(bookDtos);

    List<BookDto> result = bookController.getAllBooks();

    List<BookDto> expected = createBookDtos();
    assertEquals(expected, result);
  }
}
