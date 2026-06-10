package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Follow;
import com.aivle12.book_backend.dto.FollowResponseDto;
import com.aivle12.book_backend.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    // 팔로우
    public FollowResponseDto follow(Long followingId, Long userId) {
        Follow follow = new Follow();
        follow.setFollowerId(userId);
        follow.setFollowingId(followingId);
        follow.setCreatedAt(LocalDateTime.now());
        return toDto(followRepository.save(follow));
    }

    // 언팔로우
    public void unfollow(Long followingId, Long userId) {
        followRepository.findByFollowerId(userId).stream()
                .filter(f -> f.getFollowingId().equals(followingId))
                .findFirst()
                .ifPresent(f -> followRepository.deleteById(f.getId()));
    }

    // 내가 팔로우한 목록
    public List<FollowResponseDto> getFollowings(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // 나를 팔로우한 목록
    public List<FollowResponseDto> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // Entity → DTO 변환
    private FollowResponseDto toDto(Follow follow) {
        FollowResponseDto dto = new FollowResponseDto();
        dto.setId(follow.getId());
        dto.setFollowerId(follow.getFollowerId());
        dto.setFolloweeId(follow.getFollowingId());
        dto.setCreatedAt(follow.getCreatedAt());
        return dto;
    }
}