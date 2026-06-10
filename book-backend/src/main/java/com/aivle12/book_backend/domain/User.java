package com.aivle12.book_backend.domain;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.PrePersist;

import jakarta.persistence.Table;

import lombok.AccessLevel;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity

@Table(name = "users")

@Getter

@NoArgsConstructor(access = AccessLevel.PROTECTED)

@AllArgsConstructor

public class User {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, unique = true)

    private String email;

    @Column(nullable = false)

    private String password;

    @Column(nullable = false, unique = true)

    private String nickname;

    @Column(nullable = false, updatable = false)

    private LocalDateTime createdAt;

    public User(String email, String password, String nickname) {

        this.email = email;

        this.password = password;

        this.nickname = nickname;

    }

    @PrePersist

    public void prePersist() {

        this.createdAt = LocalDateTime.now();

    }

}

