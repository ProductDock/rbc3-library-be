package com.library.rbc.service.bookservice;

import static com.library.rbc.service.bookservice.BookServiceSetUp.BOOK_ID;
import static com.library.rbc.service.bookservice.BookServiceSetUp.PAGE_NUMBER;
import static com.library.rbc.service.bookservice.BookServiceSetUp.PAGE_SIZE;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBook;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDto;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDtosPage;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBooksPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.rbc.exceptionHandler.BookNotFoundException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.service.BookService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
    Book book = createBook();
    BookDto bookDto = createBookDto();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookRepository.findAll(pageable)).thenReturn(createBooksPage());
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    Page<BookDto> result = bookService.getAllBooks(pageable);

    Page<BookDto> expectedBookDtos = createBookDtosPage();
    assertEquals(expectedBookDtos, result);
  }

  @Test
  void getBook() {
    Book book = createBook();
    BookDto bookDto = createBookDto();

    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    BookDto result = bookService.getBook(BOOK_ID);

    assertEquals(bookDto, result);
  }

  @Test
  void catchBookNotFoundException() {
    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookService.getBook(BOOK_ID);
    });

    String expectedMessage = "There is no book with id: " + BOOK_ID;
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    verify(bookMapper, never()).bookToBookDto(any());
  }

  @Test
  void addNewBook() {
    Book book = createBook();
    BookDto expected = createBookDto();

    when(bookMapper.bookDtoToBook(expected)).thenReturn(book);
    when(bookRepository.save(book)).thenReturn(book);
    BookDto actual = bookService.addNewBook(expected);

    assertEquals(expected, actual);
  }
}
