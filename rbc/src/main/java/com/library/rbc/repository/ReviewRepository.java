package com.library.rbc.repository;

import com.library.rbc.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

  Page<Review> findByBookId(String bookId, Pageable pageable);
}
