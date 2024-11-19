package com.library.rbc.model.dto;

import java.io.InputStream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.MediaType;

@Data
@AllArgsConstructor
public class ImageWithMediaTypeDto {

  private InputStream imageInputStream;
  private MediaType mediaType;
}
