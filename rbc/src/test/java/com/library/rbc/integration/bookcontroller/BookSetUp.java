package com.library.rbc.integration.bookcontroller;

import com.library.rbc.model.Author;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.AuthorDto;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.enums.BookCategory;
import com.library.rbc.model.enums.BookType;
import java.util.List;

public class BookSetUp {

  static final Author BOOK_AUTHOR = Author.builder().id("1").fullName("J. K. Rowling").build();
  static final AuthorDto BOOK_AUTHOR_DTO = AuthorDto.builder().id("1").fullName("J. K. Rowling").build();

  static final String BOOK_ID = "1";
  static final String BOOK_TITLE = "Harry Potter";
  static final List<Author> BOOK_AUTHORS = List.of(BOOK_AUTHOR);

  static final List<AuthorDto> BOOK_AUTHORS_DTO = List.of(BOOK_AUTHOR_DTO);

  static final String BOOK_IMAGE_URL = "image_url";
  static final int BOOK_NUMBER_OF_AVAILABLE_COPIES = 3;
  static final List<String> USERS_WHO_FAVORITED_BOOK = List.of();
  static final List<String> USERS_ON_WAITING_LIST = List.of();
  static final List<String> USERS_WHO_RENTED = List.of();
  static final List<String> USERS_WHO_RESERVED = List.of();
  static final List<BookCategory> BOOK_CATEGORIES = List.of();

  static final List<BookCategoryDto> BOOK_CATEGORIES_DTO = List.of();

  static final String BOOK_ISBN = "111";
  static final String BOOK_DESCRIPTION = "description";
  static final int BOOK_TOTAL_NUMBER_OF_COPIES = 3;
  static final BookType BOOK_TYPE = BookType.BOOK;
  static final List<String> USERS_WHO_SUGGESTED_BOOK = List.of();

  static final String BOOK_2_ID = "2";

  static Book createBook1() {
    return Book.builder()
        .id(BOOK_ID)
        .isbn(BOOK_ISBN)
        .title(BOOK_TITLE)
        .description(BOOK_DESCRIPTION)
        .authors(BOOK_AUTHORS)
        .imageUrl(BOOK_IMAGE_URL)
        .totalNumberOfCopies(BOOK_TOTAL_NUMBER_OF_COPIES)
        .bookType(BOOK_TYPE)
        .numberOfAvailableCopies(BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .usersWhoSuggested(USERS_WHO_SUGGESTED_BOOK)
        .bookCategories(BOOK_CATEGORIES)
        .build();
  }

  static Book createBook2() {
    return Book.builder()
        .id(BOOK_2_ID)
        .isbn(BOOK_ISBN)
        .title(BOOK_TITLE)
        .description(BOOK_DESCRIPTION)
        .authors(BOOK_AUTHORS)
        .imageUrl(BOOK_IMAGE_URL)
        .totalNumberOfCopies(BOOK_TOTAL_NUMBER_OF_COPIES)
        .bookType(BOOK_TYPE)
        .numberOfAvailableCopies(BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .usersWhoSuggested(USERS_WHO_SUGGESTED_BOOK)
        .bookCategories(BOOK_CATEGORIES)
        .build();
  }

  static BookDto createBookDto() {
    return BookDto.builder()
        .title(BOOK_TITLE)
        .authors(BOOK_AUTHORS_DTO)
        .imageUrl(BOOK_IMAGE_URL)
        .numberOfAvailableCopies(BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .bookCategories(BOOK_CATEGORIES_DTO)
        .build();
  }
}
