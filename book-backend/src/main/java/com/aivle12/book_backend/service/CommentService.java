package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Comment;
import com.aivle12.book_backend.dto.CommentRequestDto;
import com.aivle12.book_backend.dto.CommentResponseDto;
import com.aivle12.book_backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글 목록 조회
    public List<CommentResponseDto> getComments(Long bookId) {
        return commentRepository.findByBookId(bookId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 댓글 등록
    public CommentResponseDto createComment(Long bookId, CommentRequestDto dto) {
        Comment comment = new Comment();
        comment.setBookId(bookId);
        comment.setUserId(1L); // 임시 userId (나중에 로그인 연동)
        comment.setContent(dto.getContent());
        comment.setRating(dto.getRating());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    // 댓글 수정
    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("댓글을 찾을 수 없습니다"));
        comment.setContent(dto.getContent());
        comment.setRating(dto.getRating());
        comment.setUpdatedAt(LocalDateTime.now());
        return toDto(commentRepository.save(comment));
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // Entity → DTO 변환
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