package com.library.rbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRentalDetails {
    private String id;
    private String title;
    private List<Author> authors;
    private String imageUrl;
}
