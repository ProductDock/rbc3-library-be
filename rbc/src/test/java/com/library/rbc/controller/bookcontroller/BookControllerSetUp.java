package com.library.rbc.controller.bookcontroller;

import com.library.rbc.model.dto.AuthorDto;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookControllerSetUp {

  static final AuthorDto BOOK_AUTHOR_DTO = AuthorDto.builder().id("1").fullName("J. K. Rowling")
      .build();

  static final String BOOK_ID = "1";
  static final String BOOK_TITLE = "Harry Potter";
  static final List<AuthorDto> BOOK_AUTHOR_DTOS = List.of(BOOK_AUTHOR_DTO);
  static final String BOOK_IMAGE_URL = "image_url";
  static final int BOOK_NUMBER_OF_AVAILABLE_COPIES = 3;
  static final List<String> USERS_WHO_FAVORITED_BOOK = List.of();
  static final List<String> USERS_ON_WAITING_LIST = List.of();
  static final List<String> USERS_WHO_RENTED = List.of();
  static final List<String> USERS_WHO_RESERVED = List.of();
  static final List<BookCategoryDto> BOOK_CATEGORY_DTOS = List.of();

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

  static BookDto createBookDto() {
    return BookDto.builder()
        .id(BOOK_ID)
        .title(BOOK_TITLE)
        .authors(BOOK_AUTHOR_DTOS)
        .imageUrl(BOOK_IMAGE_URL)
        .numberOfAvailableCopies(BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .bookCategories(BOOK_CATEGORY_DTOS)
        .build();
  }

  static Page<BookDto> createBookDtos() {
    BookDto bookDto = createBookDto();
    List<BookDto> bookDtos = List.of(bookDto);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(bookDtos, pageable, bookDtos.size());
  }
}
