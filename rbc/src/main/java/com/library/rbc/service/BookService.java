package com.library.rbc.service;

import com.library.rbc.exceptionhandler.BookNotFoundException;
import com.library.rbc.exceptionhandler.ImageUploadException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.repository.BookRepository;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  public Page<BookDto> getAllBooks(Pageable pageable) {
    return bookRepository.findAll(pageable)
        .map(bookMapper::bookToBookDto);
  }

  public BookDto getBook(String id) {
    return bookMapper.bookToBookDto(
        bookRepository.findById(id)
            .orElseThrow(
                () -> new BookNotFoundException("Book with ID " + id + " was not found.")));
  }

  public BookDto addNewBook(BookDto bookDTO) {
    Book savedBook = bookMapper.bookDtoToBook(bookDTO);
    bookRepository.save(savedBook);
    return bookDTO;
  }


  public String uploadImage(MultipartFile image){
    String homeDirectory = System.getProperty("user.home");
    Path uploadPath = Paths.get(homeDirectory, "Documents/images");
    try {
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }
      InputStream inputStream = image.getInputStream();
      Path filePath = uploadPath.resolve(Objects.requireNonNull(image.getOriginalFilename()));
      Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException e) {
      throw new ImageUploadException("Failed uploading image");
    }
    return uploadPath+image.getOriginalFilename();
  }
}
