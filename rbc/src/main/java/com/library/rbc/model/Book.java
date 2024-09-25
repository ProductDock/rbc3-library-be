package com.library.rbc.model;

import com.library.rbc.model.enums.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "books")
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
    private Author author;
    private String imageUrl;
    private Integer totalNumberOfCopies;
    private List<String> favourites; //collection of user ids
    private List<String> watingList; //collection of user ids
    private List<String> suggestions; //collection of user ids
    private BookCategory bookCategory;
}