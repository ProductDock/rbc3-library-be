package com.library.rbc.model.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDto {

  private String id;
  private String title;
  private List<AuthorDto> authors;
  private String imageUrl;
  private Integer numberOfAvailableCopies;
  private List<String> usersWhoFavourited;
  private List<String> usersOnWaitingList;
  private List<String> usersWhoRented;
  private List<String> usersWhoReserved;
  private List<BookCategoryDto> bookCategories;
}
