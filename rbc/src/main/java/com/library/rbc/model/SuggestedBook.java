package com.library.rbc.model;

import com.library.rbc.model.enums.BookType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuggestedBook {
    private String id;
    private String isbn;
    private String title;
    private String suggestedBy;
    private BookType bookType;
}
