package com.library.rbc.repository;

import com.library.rbc.model.Book;
import com.library.rbc.model.dto.BookCategoryDto;
import com.library.rbc.model.dto.BookDto;
import com.library.rbc.model.dto.BookStatusDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

  Page<BookDto> findByBookCategoriesIn(Pageable pageable, List<BookCategoryDto> bookCategories);
  Page<BookDto> findByBookStatusIn(Pageable pageable, List<BookStatusDto> bookStatuses);
  Page<BookDto> findByBookStatusInAndBookCategoriesIn(Pageable pageable, List<BookStatusDto> bookStatuses, List<BookCategoryDto> bookCategories);}
