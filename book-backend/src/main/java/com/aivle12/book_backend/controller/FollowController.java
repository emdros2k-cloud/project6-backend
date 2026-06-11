package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.FollowResponseDto;
import com.aivle12.book_backend.dto.FollowStatusResponse;
import com.aivle12.book_backend.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // POST /authors/{id}/follows
    @PostMapping("/authors/{id}/follows")
    public ResponseEntity<FollowResponseDto> follow(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.status(201).body(followService.follow(id, userId));
    }

    // DELETE /authors/{id}/follows
    @DeleteMapping("/authors/{id}/follows")
    public ResponseEntity<Void> unfollow(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId) {
        followService.unfollow(id, userId);
        return ResponseEntity.noContent().build();
    }

    // GET /authors/{id}/follows/status
    @GetMapping("/authors/{id}/follows/status")
    public ResponseEntity<FollowStatusResponse> followStatus(
            @PathVariable Long id,
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(followService.isFollowing(id, userId));
    }

    // GET /users/followings
    @GetMapping("/users/followings")
    public ResponseEntity<List<FollowResponseDto>> getFollowings(
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(followService.getFollowings(userId));
    }

    // GET /users/followers
    @GetMapping("/users/followers")
    public ResponseEntity<List<FollowResponseDto>> getFollowers(
            @AuthenticationPrincipal Long userId) {
        return ResponseEntity.ok(followService.getFollowers(userId));
    }
}