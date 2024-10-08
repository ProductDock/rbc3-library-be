package com.library.rbc.service.bookService;

import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.library.rbc.service.bookService.BookServiceSetUp.createBookDtos;
import static com.library.rbc.service.bookService.BookServiceSetUp.createBooks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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
        var books = createBooks();
        var bookDtos = createBookDtos();
        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.booksToBookDtos(books)).thenReturn(bookDtos);

        var result = bookService.getAllBooks();

        var expectedBookDtos = createBookDtos();
        assertEquals(expectedBookDtos, result);
    }
}
