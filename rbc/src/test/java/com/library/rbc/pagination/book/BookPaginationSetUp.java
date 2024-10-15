package com.library.rbc.pagination.book;

import com.library.rbc.model.dto.AuthorDto;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookPaginationSetUp {

  static final AuthorDto BOOK_AUTHOR_DTO = AuthorDto.builder().id("1").fullName("J. K. Rowling")
      .build();

  static final String BOOK_ID_1 = "1";
  static final String BOOK_ID_2 = "2";
  static final String BOOK_ID_3 = "3";

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
  static final int PAGE_SIZE = 2;

  static BookDto createBookDto1() {
    return BookDto.builder()
        .id(BOOK_ID_1)
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

  static BookDto createBookDto2() {
    BookDto bookDto2 = createBookDto1();
    bookDto2.setId(BOOK_ID_2);
    return bookDto2;
  }

  static BookDto createBookDto3() {
    BookDto bookDto3 = createBookDto1();
    bookDto3.setId(BOOK_ID_3);
    return bookDto3;
  }

  static Page<BookDto> createBookDtos() {
    BookDto bookDto1 = createBookDto1();
    BookDto bookDto2 = createBookDto2();
    BookDto bookDto3 = createBookDto3();

    List<BookDto> bookDtos = List.of(bookDto1, bookDto2, bookDto3);
    Pageable pageable = PageRequest.of(0, 2); // Page 0, size 10

    return new PageImpl<>(bookDtos, pageable, bookDtos.size());
  }
}
