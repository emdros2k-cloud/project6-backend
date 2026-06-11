package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Follow;
import com.aivle12.book_backend.dto.FollowResponseDto;
import com.aivle12.book_backend.exception.FollowAlreadyExistsException;
import com.aivle12.book_backend.exception.FollowNotFoundException;
import com.aivle12.book_backend.repository.FollowRepository;
import com.aivle12.book_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowResponseDto follow(Long followingId, Long userId) {
        if (userId.equals(followingId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "자기 자신을 팔로우할 수 없습니다.");
        }
        if (!userRepository.existsById(followingId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.");
        }
        if (followRepository.existsByFollowerIdAndFollowingId(userId, followingId)) {
            throw new FollowAlreadyExistsException(userId, followingId);
        }
        Follow follow = new Follow();
        follow.setFollowerId(userId);
        follow.setFollowingId(followingId);
        follow.setCreatedAt(LocalDateTime.now());
        return toDto(followRepository.save(follow));
    }

    public void unfollow(Long followingId, Long userId) {
        if (!followRepository.existsByFollowerIdAndFollowingId(userId, followingId)) {
            throw new FollowNotFoundException(userId, followingId);
        }
        followRepository.deleteByFollowerIdAndFollowingId(userId, followingId);
    }

    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollowings(Long userId) {
        return followRepository.findByFollowerId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FollowResponseDto> getFollowers(Long userId) {
        return followRepository.findByFollowingId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private FollowResponseDto toDto(Follow follow) {
        FollowResponseDto dto = new FollowResponseDto();
        dto.setId(follow.getId());
        dto.setFollowerId(follow.getFollowerId());
        dto.setFolloweeId(follow.getFollowingId());
        dto.setCreatedAt(follow.getCreatedAt());
        return dto;
    }
}
