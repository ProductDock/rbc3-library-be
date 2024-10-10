package com.library.rbc.controller.bookController;

import com.library.rbc.controller.BookController;
import com.library.rbc.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static com.library.rbc.controller.bookController.BookControllerSetUp.createBookDtos;
import static com.library.rbc.controller.bookController.BookControllerSetUp.createResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookControllerShould {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Test
    void getAllBooks() {
        var bookDtos = createBookDtos();
        when(bookService.getAllBooks()).thenReturn(bookDtos);

        var result = bookController.getAllBooks();

        var expeted = createResponseEntity();
        assertEquals(expeted, result);
    }
}
