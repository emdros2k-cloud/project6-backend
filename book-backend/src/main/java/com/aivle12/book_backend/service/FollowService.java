package com.aivle12.book_backend.service;

import com.aivle12.book_backend.dto.FollowResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    public FollowResponseDto follow(Long id) {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public void unfollow(Long id) {
        // 백엔드 2 팀원이 채울 예정
    }

    public List<FollowResponseDto> getFollowings() {
        return null; // 백엔드 2 팀원이 채울 예정
    }

    public List<FollowResponseDto> getFollowers() {
        return null; // 백엔드 2 팀원이 채울 예정
    }
}