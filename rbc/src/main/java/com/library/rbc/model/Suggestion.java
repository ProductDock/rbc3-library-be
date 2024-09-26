package com.library.rbc.model;

import com.library.rbc.model.enums.SuggestionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("suggestions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Suggestion {

    @Id
    private String id;
    private SuggestedBook suggestedBook;
    private SuggestionStatus suggestionStatus;
    private String orderFrom;
    private Integer amount;
    private LocalDateTime creationDate;
}
