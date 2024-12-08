package com.library.rbc.service;

import com.library.rbc.exceptionhandler.BookNotFoundException;
import com.library.rbc.exceptionhandler.CategoryBadRequestException;
import com.library.rbc.exceptionhandler.StatusBadRequestException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.model.dto.BookStatusDto;
import com.library.rbc.repository.BookRepository;
import java.util.List;
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
            .orElseThrow(
                () -> new BookNotFoundException("Book with ID " + id + " was not found.")));
  }

  public BookDto addNewBook(BookDto bookDTO) {
    Book savedBook = bookMapper.bookDtoToBook(bookDTO);
    bookRepository.save(savedBook);
    return bookDTO;
  }

  public Page<BookDto> getBooksBy(Pageable pageable,
      List<String> bookCategories,
      List<String> bookStatuses) {
    if (bookCategories == null){
      return returnBooksByStatuses(pageable, bookStatuses);
    }
      return returnBooksByCategoriesAndStatuses(pageable,bookStatuses,bookCategories);
  }

  private Page<BookDto> returnBooksByStatuses (Pageable pageable, List<String> bookStatuses){
    if(bookStatuses == null){
      return getAllBooks(pageable);
    }
    List<BookStatusDto> statuses = convertStringsToBookStatusesDto(bookStatuses);
    return bookRepository.findByBookStatusIn(pageable, statuses);
  }

  private Page<BookDto> returnBooksByCategoriesAndStatuses(Pageable pageable, List<String> bookStatuses, List<String> bookCategories){
    List<BookCategoryDto> categories = convertStringsToBookCategoriesDto(bookCategories);
    if (bookStatuses == null) {
      return bookRepository.findByBookCategoriesIn(pageable, categories);
    }
      List<BookStatusDto> statuses = convertStringsToBookStatusesDto(bookStatuses);
      return bookRepository.findByBookStatusInAndBookCategoriesIn(pageable, statuses, categories);
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

  private List<BookStatusDto> convertStringsToBookStatusesDto(List<String> bookStatuses) {
    return bookStatuses.stream()
        .map(status -> {
          try {
            return BookStatusDto.valueOf(status.toUpperCase());
          } catch (IllegalArgumentException e) {
            throw new StatusBadRequestException("Provided status does not exist");
          }
        }).toList();
  }


}

