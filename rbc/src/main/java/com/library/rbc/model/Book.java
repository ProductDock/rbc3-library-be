package com.library.rbc.model;

import com.library.rbc.model.enums.BookCategory;
import com.library.rbc.model.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("books")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private String id;
    private String isbn;
    private String title;
    private String description;
    private List<Author> authors;
    private String imageUrl;
    private Integer totalNumberOfCopies;
    private BookType bookType;
    private Integer numberOfAvailableCopies;
    private List<String> usersWhoFavourited;
    private List<String> usersOnWaitingList;
    private List<String> usersWhoRented;
    private List<String> usersWhoReserved;
    private List<String> usersWhoSuggested;
    private List<BookCategory> bookCategories;
}