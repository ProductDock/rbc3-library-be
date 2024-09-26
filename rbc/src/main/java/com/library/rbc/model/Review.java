package com.library.rbc.model;

import com.library.rbc.model.enums.Seniority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document("reviews")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private String id;
    private Integer rating;
    private String content;
    private List<Seniority> seniorities;
    private LocalDateTime dateTime;
    private String bookId;
    private UserDetails userDetails;
}
