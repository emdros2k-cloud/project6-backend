package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.UserRequestDto;
import com.aivle12.book_backend.dto.UserResponseDto;
import com.aivle12.book_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // POST /users/signup
    @PostMapping("/users/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.status(201).body(userService.signup(dto));
    }

    // POST /users/login
    @PostMapping("/users/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

    // POST /users/logout
    @PostMapping("/users/logout")
    public ResponseEntity<Void> logout() {
        userService.logout();
        return ResponseEntity.ok().build();
    }

    // GET /users/me
    @GetMapping("/users/me")
    public ResponseEntity<UserResponseDto> getMyPage() {
        return ResponseEntity.ok(userService.getMyPage());
    }

    // GET /users/check-nickname
    @GetMapping("/users/check-nickname")
    public ResponseEntity<Void> checkNickname(@RequestParam String nickname) {
        userService.checkNickname(nickname);
        return ResponseEntity.ok().build();
    }

    // GET /users/check-email
    @GetMapping("/users/check-email")
    public ResponseEntity<Void> checkEmail(@RequestParam String email) {
        userService.checkEmail(email);
        return ResponseEntity.ok().build();
    }

    // GET /users/profile
    @GetMapping("/users/profile")
    public ResponseEntity<UserResponseDto> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }

    // PATCH /users/profile
    @PatchMapping("/users/profile")
    public ResponseEntity<UserResponseDto> updateProfile(@RequestBody @Valid UserRequestDto dto) {
        return ResponseEntity.ok(userService.updateProfile(dto));
    }
}