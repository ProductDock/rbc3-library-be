package com.library.rbc.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;

@Data
@AllArgsConstructor
public class ImageWithMediaTypeDto {

  private byte[] inputStreamResource;
  private MediaType mediaType;
}
