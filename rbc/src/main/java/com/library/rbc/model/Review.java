package com.library.rbc.model;

import com.library.rbc.model.enums.Seniority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private String id;
    private Double rating;
    private String content;
    private Seniority seniority;
    private LocalDateTime dateTime;
    private String bookId;
    private User user;
}
