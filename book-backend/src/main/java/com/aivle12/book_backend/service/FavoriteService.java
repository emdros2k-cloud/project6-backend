package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Favorite;
import com.aivle12.book_backend.dto.FavoriteRequest;
import com.aivle12.book_backend.dto.FavoriteResponse;
import com.aivle12.book_backend.exception.BookNotFoundException;
import com.aivle12.book_backend.exception.FavoriteAlreadyExistsException;
import com.aivle12.book_backend.exception.FavoriteNotFoundException;
import com.aivle12.book_backend.repository.BookRepository;
import com.aivle12.book_backend.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final BookRepository bookRepository;

    public FavoriteResponse addFavorite(Long bookId, FavoriteRequest request) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        if (favoriteRepository.existsByUserIdAndBookId(request.getUserId(), bookId)) {
            throw new FavoriteAlreadyExistsException(request.getUserId(), bookId);
        }

        Favorite favorite = new Favorite();
        favorite.setUserId(request.getUserId());
        favorite.setBookId(bookId);
        return FavoriteResponse.from(favoriteRepository.save(favorite));
    }

    public void removeFavorite(Long bookId, FavoriteRequest request) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }
        if (!favoriteRepository.existsByUserIdAndBookId(request.getUserId(), bookId)) {
            throw new FavoriteNotFoundException(request.getUserId(), bookId);
        }
        favoriteRepository.deleteByUserIdAndBookId(request.getUserId(), bookId);
    }
}
