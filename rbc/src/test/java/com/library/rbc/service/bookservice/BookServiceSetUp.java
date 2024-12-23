package com.library.rbc.service.bookservice;

import com.library.rbc.model.Author;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.AuthorDto;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookStatusDto;
import com.library.rbc.model.dto.ImageWithMediaTypeDto;
import com.library.rbc.model.enums.BookCategory;
import com.library.rbc.model.enums.BookStatus;
import com.library.rbc.model.enums.BookType;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

public class BookServiceSetUp {

  static final Author BOOK_AUTHOR = Author.builder().id("1").fullName("J. K. Rowling")
      .build();
  static final AuthorDto BOOK_AUTHOR_DTO = AuthorDto.builder().id("1").fullName("J. K. Rowling")
      .build();

  static final String BOOK_ID = "1";
  static final String BOOK_TITLE = "Harry Potter";
  static final List<AuthorDto> BOOK_AUTHOR_DTOS = List.of(BOOK_AUTHOR_DTO);
  static final List<Author> BOOK_AUTHORS = List.of(BOOK_AUTHOR);
  static final String BOOK_IMAGE_URL = "image_url";
  static final int BOOK_NUMBER_OF_AVAILABLE_COPIES = 3;
  static final BookStatus BOOK_STATUS= BookStatus.AVAILABLE;
  static final BookStatusDto BOOK_STATUS_DTO = BookStatusDto.AVAILABLE;
  static final List<String> USERS_WHO_FAVORITED_BOOK = List.of();
  static final List<String> USERS_ON_WAITING_LIST = List.of();
  static final List<String> USERS_WHO_RENTED = List.of();
  static final List<String> USERS_WHO_RESERVED = List.of();
  static final List<BookCategory> BOOK_CATEGORIES = List.of();
  static final List<BookCategoryDto> BOOK_CATEGORY_DTOS = List.of();

  static final String BOOK_ISBN = "111";
  static final String BOOK_DESCRIPTION = "description";
  static final int BOOK_TOTAL_NUMBER_OF_COPIES = 3;
  static final BookType BOOK_TYPE = BookType.BOOK;
  static final List<String> USERS_WHO_SUGGESTED_BOOK = List.of();

  static final int PAGE_NUMBER = 0;
  static final int PAGE_SIZE = 10;

  static Book createBook() {
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
        .bookStatus(BOOK_STATUS)
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
        .id(BOOK_ID)
        .title(BOOK_TITLE)
        .authors(BOOK_AUTHOR_DTOS)
        .imageUrl(BOOK_IMAGE_URL)
        .numberOfAvailableCopies(BOOK_NUMBER_OF_AVAILABLE_COPIES)
        .bookStatus(BOOK_STATUS_DTO)
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .bookCategories(BOOK_CATEGORY_DTOS)
        .build();
  }

  static List<Book> createBooks() {
    Book book = createBook();
    return List.of(book);
  }

  static Page<BookDto> createBookDtosPage() {
    BookDto bookDto = createBookDto();
    List<BookDto> bookDtos = List.of(bookDto);
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(bookDtos, pageable, bookDtos.size());
  }

  static Page<Book> createBooksPage() {
    List<Book> books = createBooks();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    return new PageImpl<>(books, pageable, books.size());
  }

  static ImageWithMediaTypeDto createImageWithMediaTypeDto() {
    return new ImageWithMediaTypeDto(
        new byte[]{},
        MediaType.IMAGE_JPEG
    );
  }
}
