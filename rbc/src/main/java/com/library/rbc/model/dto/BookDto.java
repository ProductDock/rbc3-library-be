package com.library.rbc.model.dto;

import com.library.rbc.model.Author;
import com.library.rbc.model.enums.BookCategory;
import com.library.rbc.model.enums.BookType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDto {
    private String id;
    private String title;
    private List<Author> authors;
    private String imageUrl;
    private Integer numberOfAvailableCopies;
    private List<String> usersWhoFavourited;
    private List<String> usersOnWaitingList;
    private List<String> usersWhoRented;
    private List<String> usersWhoReserved;
    private List<BookCategory> bookCategories;
}
