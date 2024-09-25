package com.library.rbc.model;

import com.library.rbc.model.enums.SuggestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "suggestions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {

    @Id
    private String id;
    private Book book;
    private SuggestionStatus suggestionStatus;
    private String orderFrom;
    private Integer amount;
    private LocalDateTime creatingTime;
}
