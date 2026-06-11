package com.aivle12.book_backend.repository;

import com.aivle12.book_backend.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfileRepository
        extends JpaRepository<Profile, Long>{
    Optional<Profile> findByUserEmail(String email);

    boolean existsByUserEmail(String email);
    void deleteByUserEmail(String email);
}