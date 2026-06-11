package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")


public class ProfileController{

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    @GetMapping("/profile/all")
    public List<Profile>allProfile() {return profileService.findAll();
    }

    @GetMapping("/profile/{email}")
    public Profile getProfileEmail(@PathVariable String email){
        return profileService.searchByEmail(email);
    }

    /*
    @PostMapping("/profile/{email}")
    public Profile addProfileEmail(@PathVariable("email") String email, @RequestBody Profile profile){
        profile.setEmail(email);
        return profileService.create(profile);
    }
    @PatchMapping("/profile/{email}")
    public Profile updateProfileEmail(@PathVariable String email, @RequestBody Profile profile){
        return profileService.update(email, profile);
    }

    @DeleteMapping("profile/{email}")
    public ResponseEntity<Profile> deleteProfileEmail(@PathVariable String email){
        profileService.delete(email);
        return ResponseEntity.noContent().build();

    }

     */

    //'로그인한 사용자'의 프로필을 다루어야 한다

    @PostMapping("/profile")
    public Profile addProfile(@AuthenticationPrincipal Long userId, @RequestBody Profile profile){
        profile.setId(userId);
        return profileService.create(profile);
    }

    @GetMapping("/profile")
    public Profile getProfile(@AuthenticationPrincipal Long userId){
        return profileService.searchById(userId);
    }

    @PatchMapping("/profile")
    public Profile updateProfile(@AuthenticationPrincipal Long userId, @RequestBody Profile profile){
        return profileService.update(userId, profile);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<Void> deleteProfile(@AuthenticationPrincipal Long userId){
        profileService.delete(userId);
        return ResponseEntity.noContent().build();
    }

}

