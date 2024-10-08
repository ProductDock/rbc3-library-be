package com.library.rbc.model.dto;

import com.library.rbc.model.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto bookToBookDto(Book book);
    Book bookDtoToBook(BookDto bookDto);

    List<BookDto> booksToBookDtos (List<Book> books);
    List<Book> bookDtosToBooks (List<BookDto> bookDtos);
}
