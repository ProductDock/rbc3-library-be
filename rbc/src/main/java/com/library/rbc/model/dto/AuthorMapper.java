package com.library.rbc.model.dto;

import com.library.rbc.model.Author;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

  AuthorDto authorToAuthorDto(Author author);

  Author authorDtoToAuthor(AuthorDto authorDto);

  List<AuthorDto> authorsToAuthorDtos(List<Author> authors);

  List<Author> authorDtosToAuthors(List<AuthorDto> authorDtos);

}
