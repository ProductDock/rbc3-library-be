package com.library.rbc.service.bookservice;

import static com.library.rbc.service.bookservice.BookServiceSetUp.createBook;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDto;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDtosPage;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBooksPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
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
    Pageable pageable = PageRequest.of(0, 10);

    when(bookRepository.findAll(pageable)).thenReturn(createBooksPage());
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    Page<BookDto> result = bookService.getAllBooks(pageable);

    Page<BookDto> expectedBookDtos = createBookDtosPage();
    assertEquals(expectedBookDtos, result);
  }
}
