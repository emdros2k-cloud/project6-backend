package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")


public class ProfileController{

/*
이 파일이 필요할까? 생각해봐야 할 부분
 */

    private final ProfileRepository profileRepository;
    private final ProfileService profileService;

    @GetMapping("/profile")
    public List<Profile>allProfile() {return profileService.findAll();
    }

    @GetMapping("profile/{email}")
    public Profile getProfile(@PathVariable String email){
        return profileService.searchByEmail(email);
    }

    @PostMapping("profile/{email}")
    public Profile addProfile(@PathVariable("email") String email, @RequestBody Profile profile){
        profile.setEmail(email);
        return profileService.create(profile);
    }

    @PatchMapping("profile/{email}")
    public Profile updateProfile(@PathVariable String email, @RequestBody Profile profile){
        return profileService.update(email, profile);
    }

    @DeleteMapping("profile/{email}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable String email){
        profileService.delete(email);
        return ResponseEntity.noContent().build();

    }

}