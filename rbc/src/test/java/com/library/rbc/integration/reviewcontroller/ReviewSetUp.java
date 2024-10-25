package com.library.rbc.integration.reviewcontroller;

import com.library.rbc.model.Author;
import com.library.rbc.model.Book;
import com.library.rbc.model.Review;
import com.library.rbc.model.dto.SeniorityDto;
import com.library.rbc.model.enums.BookCategory;
import com.library.rbc.model.enums.BookType;
import com.library.rbc.model.enums.Seniority;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewSetUp {

//  static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
//      "yyyy-MM-dd'T'HH:mm:ss.SSS");

  static final String REVIEW_ID = "1";
  static final Integer REVIEW_RATING = 5;
  static final String REVIEW_CONTENT = "Content";
  static final List<Seniority> REVIEW_SENIORITIES = List.of();
  static final List<SeniorityDto> REVIEW_SENIORITY_DTOS = List.of();
  static final LocalDateTime REVIEW_DATE_TIME = LocalDateTime.now();
  static final String BOOK_ID = "1";
  static final String USER_ID = "1";

  static final Author BOOK_AUTHOR = Author.builder().id("1").fullName("J. K. Rowling").build();

  static final String BOOK_TITLE = "Harry Potter";
  static final List<Author> BOOK_AUTHORS = List.of(BOOK_AUTHOR);
  static final String BOOK_IMAGE_URL = "image_url";
  static final int BOOK_NUMBER_OF_AVAILABLE_COPIES = 3;
  static final List<String> USERS_WHO_FAVORITED_BOOK = List.of();
  static final List<String> USERS_ON_WAITING_LIST = List.of();
  static final List<String> USERS_WHO_RENTED = List.of();
  static final List<String> USERS_WHO_RESERVED = List.of();
  static final List<BookCategory> BOOK_CATEGORIES = List.of();

  static final String BOOK_ISBN = "111";
  static final String BOOK_DESCRIPTION = "description";
  static final int BOOK_TOTAL_NUMBER_OF_COPIES = 3;
  static final BookType BOOK_TYPE = BookType.BOOK;
  static final List<String> USERS_WHO_SUGGESTED_BOOK = List.of();

  static final String REVIEW_ID_2 = "2";
  static final String USER_ID_2 = "2";

//  static final String formattedDateTime = REVIEW_DATE_TIME.format(formatter);

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
        .usersWhoFavourited(USERS_WHO_FAVORITED_BOOK)
        .usersOnWaitingList(USERS_ON_WAITING_LIST)
        .usersWhoRented(USERS_WHO_RENTED)
        .usersWhoReserved(USERS_WHO_RESERVED)
        .usersWhoSuggested(USERS_WHO_SUGGESTED_BOOK)
        .bookCategories(BOOK_CATEGORIES)
        .build();
  }

  static Review createReview1() {
    return Review.builder()
        .id(REVIEW_ID)
        .rating(REVIEW_RATING)
        .content(REVIEW_CONTENT)
        .seniorities(REVIEW_SENIORITIES)
        .dateTime(REVIEW_DATE_TIME)
        .bookId(BOOK_ID)
        .userId(USER_ID)
        .build();
  }

  static Review createReview2() {
    Review review2 = createReview1();
    review2.setId(REVIEW_ID_2);
    review2.setUserId(USER_ID_2);
    return review2;
  }

}
