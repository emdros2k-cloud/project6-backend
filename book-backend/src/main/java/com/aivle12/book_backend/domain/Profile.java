package com.aivle12.book_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity//@Table(name="BOOK2")  //Table annotation test
@Table(name="profiles")
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor

public class Profile{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;//아이디(자동생성)

    @Column(nullable=false)
    private String bio;//자기소개

    @Column(nullable=false)
    private String avatar;//아바타 이미지 주소

    @Column(nullable=false)
    private LocalDateTime createdAt;//생성일자

    public Profile(String bio, String avatar) {
        this.bio = bio;
        this.avatar = avatar;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    private String email;
    //user 필드와 대응

}