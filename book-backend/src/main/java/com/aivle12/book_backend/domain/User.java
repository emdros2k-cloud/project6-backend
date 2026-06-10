package com.aivle12.book_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity//@Table(name="BOOK2")  //Table annotation test
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor

public class User{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;//id(자동생성)

    @Column(nullable=false)
    @NotBlank
    private String email;//이메일 주소

    @Column(nullable=false)
    private String password;//비번

    @Column(nullable=false)
    private String nickname;//닉네임

    @Column(nullable=false)
    private LocalDateTime createdAt;//생성일자(자동생성)

    public User (String email,String password, String nickname){
        this.email=email;
        this.password=password;
        this.nickname=nickname;
    }

    @PrePersist
    public void prePersist(){
        this.createdAt=LocalDateTime.now();
    }

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="user_email", referencedColumnName="email")
    private Profile profile;

}