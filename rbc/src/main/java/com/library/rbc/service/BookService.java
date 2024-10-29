package com.library.rbc.service;

import com.library.rbc.exceptionHandler.BookNotFoundException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  public Page<BookDto> getAllBooks(Pageable pageable) {
    return bookRepository.findAll(pageable)
        .map(bookMapper::bookToBookDto);
  }

  public BookDto getBook(String id) {
    return bookMapper.bookToBookDto(
        bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException("There is no book with id: " + id)));
  }

  public BookDto addNewBook(BookDto bookDTO) {
    Book savedBook = bookMapper.bookDtoToBook(bookDTO);
    bookRepository.save(savedBook);
    return bookDTO;
  }
}
