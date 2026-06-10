package com.aivle12.book_backend.repository;

import com.aivle12.book_backend.domain.Profile;
import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProfileRepository
        extends JpaRepository<Profile, Long>{
    Optional<Profile> findByEmail(String email);

    boolean existsByEmail(String email);
    void deleteByEmail(String email);
    //Profile findById(Long id);
}