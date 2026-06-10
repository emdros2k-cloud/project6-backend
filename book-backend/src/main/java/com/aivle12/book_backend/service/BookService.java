package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Book;
import com.aivle12.book_backend.dto.BookCoverRequest;
import com.aivle12.book_backend.dto.BookCreateRequest;
import com.aivle12.book_backend.dto.BookResponse;
import com.aivle12.book_backend.dto.BookUpdateRequest;
import com.aivle12.book_backend.exception.BookNotFoundException;
import com.aivle12.book_backend.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<BookResponse> getBooks() {
        return bookRepository.findAll().stream()
                .map(BookResponse::from)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookResponse getBook(Long id) {
        return BookResponse.from(findById(id));
    }

    public BookResponse createBook(BookCreateRequest request) {
        Book book = new Book();
        book.setAuthorId(request.getAuthorId());
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setContent(request.getContent());
        book.setCoverImageUrl(request.getCoverImageUrl());
        book.setGenre(request.getGenre());
        book.setPublisher(request.getPublisher());
        book.setPrice(request.getPrice());
        book.setPages(request.getPages());
        book.setIsbn(request.getIsbn());
        book.setPubDate(request.getPubDate());
        return BookResponse.from(bookRepository.save(book));
    }

    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = findById(id);
        if (request.getTitle() != null) book.setTitle(request.getTitle());
        if (request.getAuthor() != null) book.setAuthor(request.getAuthor());
        if (request.getContent() != null) book.setContent(request.getContent());
        if (request.getCoverImageUrl() != null) book.setCoverImageUrl(request.getCoverImageUrl());
        if (request.getGenre() != null) book.setGenre(request.getGenre());
        if (request.getPublisher() != null) book.setPublisher(request.getPublisher());
        if (request.getPrice() != null) book.setPrice(request.getPrice());
        if (request.getPages() != null) book.setPages(request.getPages());
        if (request.getIsbn() != null) book.setIsbn(request.getIsbn());
        if (request.getPubDate() != null) book.setPubDate(request.getPubDate());
        return BookResponse.from(book);
    }

    public void deleteBook(Long id) {
        findById(id);
        bookRepository.deleteById(id);
    }

    public BookResponse incrementViewCount(Long id) {
        Book book = findById(id);
        book.setViewCount(book.getViewCount() == null ? 1 : book.getViewCount() + 1);
        return BookResponse.from(book);
    }

    public BookResponse updateCover(Long id, BookCoverRequest request) {
        Book book = findById(id);
        book.setCoverImageUrl(request.getCoverImageUrl());
        return BookResponse.from(book);
    }

    private Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
