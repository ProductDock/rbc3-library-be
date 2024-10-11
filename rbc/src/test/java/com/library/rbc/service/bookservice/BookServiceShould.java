package com.library.rbc.service.bookservice;

import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDtos;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBooks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.service.BookService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BookServiceShould {

  @InjectMocks
  private BookService bookService;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private BookMapper bookMapper;

  @Test
  void getAllBooks() {
    List<Book> books = createBooks();
    List<BookDto> bookDtos = createBookDtos();
    when(bookRepository.findAll()).thenReturn(books);
    when(bookMapper.booksToBookDtos(books)).thenReturn(bookDtos);

    List<BookDto> result = bookService.getAllBooks();

    List<BookDto> expectedBookDtos = createBookDtos();
    assertEquals(expectedBookDtos, result);
  }
}
