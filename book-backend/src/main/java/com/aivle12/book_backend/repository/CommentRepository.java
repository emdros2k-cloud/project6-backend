package com.aivle12.book_backend.repository;

import com.aivle12.book_backend.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBookId(Long bookId);

    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.bookId = :bookId")
    Double averageRatingByBookId(@Param("bookId") Long bookId);

    long countByBookId(Long bookId);
}