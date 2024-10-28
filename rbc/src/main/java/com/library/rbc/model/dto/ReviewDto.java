package com.library.rbc.model.dto;

import com.library.rbc.model.enums.Seniority;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ReviewDto {

    private String id;
    private Integer rating;
    private String content;
    private List<SeniorityDto> seniorities;
    private LocalDateTime dateTime;
    private String bookId;
    private String userId;
}
