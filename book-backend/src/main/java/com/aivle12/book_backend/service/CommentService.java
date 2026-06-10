package com.aivle12.book_backend.service;

import com.aivle12.book_backend.dto.CommentRequestDto;
import com.aivle12.book_backend.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    public List<CommentResponseDto> getComments(Long bookId) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public CommentResponseDto createComment(Long bookId, CommentRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public CommentResponseDto updateComment(Long id, CommentRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void deleteComment(Long id) {
        // 백엔드 2 팀원이 채울 예정
    }
}