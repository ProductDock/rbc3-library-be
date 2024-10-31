package com.library.rbc.service;

import com.library.rbc.exceptionHandler.BookNotFoundException;
import com.library.rbc.exceptionHandler.CategoryBadRequestException;
import com.library.rbc.exceptionHandler.StatusBadRequestException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.model.enums.BookStatus;
import com.library.rbc.repository.BookRepository;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<BookDto> getBooksBy(Pageable pageable, List<String> bookCategories,
      List<String> bookStatuses) {
    return getBooksByCategoriesAndStatuses(pageable, bookCategories, bookStatuses);
  }

  private List<BookDto> getBooksByCategoriesAndStatuses(Pageable pageable,
      List<String> bookCategories,
      List<String> bookStatuses) {
    if (bookCategories.contains("ALL")) {
      if (bookStatuses.contains("ALL")) {
        return getAllBooks(pageable).stream().toList();
      } else {
        List<BookDto> allBooks = getAllBooks(pageable).stream().toList();
        return returnBooksByStatuses(allBooks, bookStatuses);
      }
    } else {
      List<BookCategoryDto> bookCategoryDto = convertStringsToBookCategoriesDto(bookCategories);
      if (bookStatuses.contains("ALL")) {
        return bookRepository.findByBookCategoriesIn(bookCategoryDto);
      } else {
        List<BookDto> booksByCategory = bookRepository.findByBookCategoriesIn(bookCategoryDto);
        return returnBooksByStatuses(booksByCategory, bookStatuses);

      }
    }
  }

  private List<BookDto> returnBooksByStatuses(List<BookDto> books, List<String> bookStatuses) {
    List<BookStatus> statuses = convertStringsToBookStatuses(bookStatuses);
    return books.stream().filter(book -> {
      if (statuses.isEmpty()) {
        return true;
      }
      return statuses.stream()
          .anyMatch(status -> switch (status) {
            case AVAILABLE -> book.getNumberOfAvailableCopies() > 0;
            case RENTED -> book.getNumberOfAvailableCopies() == 0;
            case RESERVED -> !book.getUsersWhoReserved().isEmpty()
                && book.getNumberOfAvailableCopies() == 0;
          });
    }).collect(Collectors.toList());
  }

  private List<BookCategoryDto> convertStringsToBookCategoriesDto(List<String> bookCategories) {
    return bookCategories.stream()
        .map(category -> {
          try {
            return BookCategoryDto.valueOf(category.toUpperCase());
          } catch (IllegalArgumentException e) {
            throw new CategoryBadRequestException("Provided category does not exist");
          }
        }).toList();
  }

  private List<BookStatus> convertStringsToBookStatuses(List<String> bookStatuses) {
    return bookStatuses.stream()
        .map(status -> {
          try {
            return BookStatus.valueOf(status.toUpperCase());
          } catch (IllegalArgumentException e) {
            throw new StatusBadRequestException("Provided status does not exist");
          }
        }).toList();
  }


}

