package com.aivle12.book_backend.service;

import com.aivle12.book_backend.dto.UserRequestDto;
import com.aivle12.book_backend.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    public UserResponseDto signup(UserRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public UserResponseDto login(UserRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void logout() {
        // 백엔드 2 팀원이 채울 예정
    }

    public UserResponseDto getMyPage() {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void checkNickname(String nickname) {
        // 백엔드 2 팀원이 채울 예정
    }

    public void checkEmail(String email) {
        // 백엔드 2 팀원이 채울 예정
    }

    public UserResponseDto getProfile() {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public UserResponseDto updateProfile(UserRequestDto dto) {
        return null; // 백엔드 2 팀원이 채울 예정
    }
}