package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.AvailabilityResponse;
import com.aivle12.book_backend.dto.LoginRequest;
import com.aivle12.book_backend.dto.LoginResponse;
import com.aivle12.book_backend.dto.MessageResponse;
import com.aivle12.book_backend.dto.SignupRequest;
import com.aivle12.book_backend.dto.UserResponse;
import com.aivle12.book_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public UserResponse signup(@Valid @RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse me(@AuthenticationPrincipal Long userId) {
        return authService.getMe(userId);
    }

    @GetMapping("/check-email")
    public AvailabilityResponse checkEmail(@RequestParam String email) {
        return authService.checkEmailAvailable(email);
    }

    @GetMapping("/check-nickname")
    public AvailabilityResponse checkNickname(@RequestParam String nickname) {
        return authService.checkNicknameAvailable(nickname);
    }

    @PostMapping("/logout")
    public MessageResponse logout() {
        return new MessageResponse("로그아웃되었습니다.");
    }
}