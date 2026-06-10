package com.aivle12.book_backend.service;

import com.aivle12.book_backend.service.BookService;
import com.aivle12.book_backend.dto.BookRequestDto;
import com.aivle12.book_backend.dto.BookResponseDto;
import com.aivle12.book_backend.dto.CoverRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    public List<BookResponseDto> getAllBooks() {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public BookResponseDto getBookById(Long id) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public BookResponseDto createBook(BookRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void deleteBook(Long id) {
        // 백엔드 2 팀원이 채울 예정
    }

    public BookResponseDto updateCover(Long id, CoverRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }
    public void increaseViewCount(Long id) {
        // 백엔드 2 팀원이 채울 예정
    }
}
