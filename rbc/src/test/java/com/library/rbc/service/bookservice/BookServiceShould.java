package com.library.rbc.service.bookservice;

import static com.library.rbc.service.bookservice.BookServiceSetUp.BOOK_ID;
import static com.library.rbc.service.bookservice.BookServiceSetUp.PAGE_NUMBER;
import static com.library.rbc.service.bookservice.BookServiceSetUp.PAGE_SIZE;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBook;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDto;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBookDtosPage;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createBooksPage;
import static com.library.rbc.service.bookservice.BookServiceSetUp.createImageWithMediaTypeDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.library.rbc.exceptionhandler.BookNotFoundException;
import com.library.rbc.exceptionhandler.CategoryBadRequestException;
import com.library.rbc.exceptionhandler.StatusBadRequestException;
import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookMapper;
import com.library.rbc.model.dto.BookStatusDto;
import com.library.rbc.model.dto.ImageWithMediaTypeDto;
import com.library.rbc.repository.BookRepository;
import com.library.rbc.service.BookService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class BookServiceShould {


  @InjectMocks
  private BookService bookService;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private BookMapper bookMapper;


  @Test
  void getAllBooks() {
    Book book = createBook();
    BookDto bookDto = createBookDto();
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    when(bookRepository.findAll(pageable)).thenReturn(createBooksPage());
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    Page<BookDto> result = bookService.getAllBooks(pageable);

    Page<BookDto> expectedBookDtos = createBookDtosPage();
    assertEquals(expectedBookDtos, result);
  }

  @Test
  void getBook() {
    Book book = createBook();
    BookDto bookDto = createBookDto();

    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    BookDto result = bookService.getBook(BOOK_ID);

    assertEquals(bookDto, result);
  }

  @Test
  void catchBookNotFoundException() {
    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookService.getBook(BOOK_ID);
    });

    String expectedMessage = "Book with ID " + BOOK_ID + " was not found.";
    String actualMessage = exception.getMessage();
    assertTrue(actualMessage.contains(expectedMessage));
    verify(bookMapper, never()).bookToBookDto(any());
  }

  @Test
  void addNewBook() {
    Book book = createBook();
    BookDto expected = createBookDto();

    when(bookMapper.bookDtoToBook(expected)).thenReturn(book);
    when(bookRepository.save(book)).thenReturn(book);
    BookDto actual = bookService.addNewBook(expected);

    assertEquals(expected, actual);
  }

  @Test
  void getBooksByAllStatusesAndCategories() {
    List<String> categories= null;
    List<String> statuses= null;
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    Book book = createBook();
    BookDto bookDto = createBookDto();

    when(bookRepository.findAll(pageable)).thenReturn(createBooksPage());
    when(bookMapper.bookToBookDto(book)).thenReturn(bookDto);
    Page<BookDto> actual = bookService.getBooksBy(pageable,categories,statuses);

    Page<BookDto> expected= createBookDtosPage();

    assertEquals(expected,actual);

  }
  @Test
  void getBooksByAllCategoriesAndSpecificStatus() {
    List<String> categories = null;
    List<String> statuses = List.of("AVAILABLE");
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    List<BookStatusDto>  bookStatusesDto = List.of(BookStatusDto.AVAILABLE);

    when(bookRepository.findByBookStatusIn(pageable, bookStatusesDto)).thenReturn(createBookDtosPage());
    Page<BookDto> actual = bookService.getBooksBy(pageable, categories, statuses);

    Page<BookDto> expected = createBookDtosPage();

    assertEquals(expected, actual);
  }

  @Test
  void getBooksBySpecificCategoriesAndAllStatuses(){
    List<String> statuses = null;
    List<String> categories = List.of("MARKETING");
    Pageable pageable= PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    List<BookCategoryDto> bookCategoriesDto = List.of(BookCategoryDto.MARKETING);

    when(bookRepository.findByBookCategoriesIn(pageable, bookCategoriesDto)).thenReturn(createBookDtosPage());
    Page<BookDto> actual = bookService.getBooksBy(pageable,categories,statuses);

    Page<BookDto> expected = createBookDtosPage();

    assertEquals(expected,actual);
  }

  @Test
  void getBooksBySpecificCategoriesAndStatuses(){
    List<String> statuses = List.of("AVAILABLE");
    List<String> categories = List.of("MARKETING");
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);
    List<BookCategoryDto> bookCategoriesDto = List.of(BookCategoryDto.MARKETING);
    List<BookStatusDto> bookStatusesDto = List.of(BookStatusDto.AVAILABLE);

    when(bookRepository.findByBookStatusInAndBookCategoriesIn(pageable, bookStatusesDto, bookCategoriesDto)).thenReturn(createBookDtosPage());
    Page<BookDto> actual = bookService.getBooksBy(pageable, categories, statuses);

    Page<BookDto> expected = createBookDtosPage();

    assertEquals(expected, actual);
  }

  @Test
  void throwCategoryBadRequestException() {
    List<String> invalidCategories = List.of("INVALID_CATEGORY");
    List<String> validStatuses = List.of("AVAILABLE");
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    CategoryBadRequestException exception = assertThrows(CategoryBadRequestException.class, () -> {
      bookService.getBooksBy(pageable, invalidCategories, validStatuses);
    });

    assertEquals("Provided category does not exist", exception.getMessage());
  }

  @Test
  void throwStatusBadRequestException() {
    List<String> validCategories = List.of("MARKETING");
    List<String> invalidStatuses = List.of("INVALID_STATUS");
    Pageable pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE);

    StatusBadRequestException exception = assertThrows(StatusBadRequestException.class, () -> {
      bookService.getBooksBy(pageable, validCategories, invalidStatuses);
    });

    assertEquals("Provided status does not exist", exception.getMessage());
  }

  @Test
  void testGetBookImageById_BookNotFound() {
    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.empty());
    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookService.getBookImageById(BOOK_ID);
    });
    assertEquals("Book with ID " + BOOK_ID + " was not found.", exception.getMessage());
  }

  @Test
  void testGetBookImageById_ImageNotFound() {
    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(createBook()));
    when(bookMapper.bookToBookDto(any(Book.class))).thenReturn(createBookDto());
    MockedStatic<Files> mockFiles = mockStatic(Files.class);
    mockFiles.when(() -> Files.readAllBytes(any(Path.class))).thenThrow(new IOException());

    BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> {
      bookService.getBookImageById(BOOK_ID);
    });
    assertEquals("Image for book with ID 1 could not be found or read.", exception.getMessage());
    mockFiles.close();
  }

  @Test
  void getBookImageById() {
    ImageWithMediaTypeDto expected = createImageWithMediaTypeDto();
    Book book = createBook();
    BookDto bookDto = createBookDto();
    MockedStatic<MediaType> mockMediaType = mockStatic(MediaType.class);
    MockedStatic<Files> mockFiles = mockStatic(Files.class);

    when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(book));
    when(bookMapper.bookToBookDto(any(Book.class))).thenReturn(bookDto);

    mockFiles.when(() -> Files.readAllBytes(any(Path.class))).thenReturn(new byte[]{});
    mockFiles.when(() -> Files.probeContentType(any(Path.class)))
        .thenReturn(MediaType.IMAGE_JPEG_VALUE);
    mockMediaType.when(() -> MediaType.valueOf(any(String.class)))
        .thenReturn(MediaType.IMAGE_JPEG);

    ImageWithMediaTypeDto actual = bookService.getBookImageById(BOOK_ID);
    assertEquals(expected, actual);

    mockFiles.close();
    mockMediaType.close();
  }

}
