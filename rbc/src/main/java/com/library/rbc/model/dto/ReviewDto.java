package com.library.rbc.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

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
