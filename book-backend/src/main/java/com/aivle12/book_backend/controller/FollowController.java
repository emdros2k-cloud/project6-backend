package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.dto.FollowResponseDto;
import com.aivle12.book_backend.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    // POST /authors/{id}/follows
    @PostMapping("/authors/{id}/follows")
    public ResponseEntity<FollowResponseDto> follow(@PathVariable Long id) {
        return ResponseEntity.status(201).body(followService.follow(id));
    }

    // DELETE /authors/{id}/follows
    @DeleteMapping("/authors/{id}/follows")
    public ResponseEntity<Void> unfollow(@PathVariable Long id) {
        followService.unfollow(id);
        return ResponseEntity.noContent().build();
    }

    // GET /users/followings
    @GetMapping("/users/followings")
    public ResponseEntity<List<FollowResponseDto>> getFollowings() {
        return ResponseEntity.ok(followService.getFollowings());
    }

    // GET /users/followers
    @GetMapping("/users/followers")
    public ResponseEntity<List<FollowResponseDto>> getFollowers() {
        return ResponseEntity.ok(followService.getFollowers());
    }
}