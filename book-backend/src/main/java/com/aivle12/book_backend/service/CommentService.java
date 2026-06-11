package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Comment;
import com.aivle12.book_backend.dto.CommentRequestDto;
import com.aivle12.book_backend.dto.CommentResponseDto;
import com.aivle12.book_backend.exception.BookNotFoundException;
import com.aivle12.book_backend.exception.CommentNotFoundException;
import com.aivle12.book_backend.repository.BookRepository;
import com.aivle12.book_backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComments(Long bookId) {
        return commentRepository.findByBookId(bookId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CommentResponseDto createComment(Long bookId, CommentRequestDto dto, Long userId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        Comment comment = new Comment();
        comment.setBookId(bookId);
        comment.setUserId(userId);
        comment.setContent(dto.getContent());
        comment.setRating(dto.getRating());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto dto, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        if (!comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글을 수정할 권한이 없습니다.");
        }
        comment.setContent(dto.getContent());
        comment.setRating(dto.getRating());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException(id));
        if (!comment.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "댓글을 삭제할 권한이 없습니다.");
        }
        commentRepository.deleteById(id);
    }

    private CommentResponseDto toDto(Comment comment) {
        CommentResponseDto dto = new CommentResponseDto();
        dto.setId(comment.getId());
        dto.setBookId(comment.getBookId());
        dto.setUserId(comment.getUserId());
        dto.setContent(comment.getContent());
        dto.setRating(comment.getRating());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        return dto;
    }
}
