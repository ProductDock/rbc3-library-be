package com.library.rbc.controller.bookcontroller;

import com.library.rbc.model.dto.AuthorDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.enums.BookCategory;
import java.util.List;

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
  static final List<BookCategory> BOOK_CATEGORIES = List.of();

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
        .bookCategories(BOOK_CATEGORIES)
        .build();
  }

  static List<BookDto> createBookDtos() {
    BookDto bookDto = createBookDto();
    return List.of(bookDto);
  }
}
