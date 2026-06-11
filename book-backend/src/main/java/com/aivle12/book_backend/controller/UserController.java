package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.domain.User;
import com.aivle12.book_backend.repository.UserRepository;
import com.aivle12.book_backend.service.UserService;
/*
import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.service.ProfileService;
 */
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController{
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/users/by-email")
    public Optional<User> getUser(@AuthenticationPrincipal Long id) {
        return userRepository.findById(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PatchMapping("/users")
    public User updateUser(@AuthenticationPrincipal Long id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @GetMapping("/users")
    public List<User> allUser() {return userService.findAll();
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
    }



    //여기는 아직 다른영역
    /*

    @GetMapping("/users/comments")
    public List<Comment> getComments(@RequestParam Long id){}

    @GetMapping("/users/me/books")
    public List<Books> getBooks(@RequestParam Long id){

    }

    @GetMapping("/users/me/favorites")
    public List<Favorites>(@RequestParam Long id){}


    @GetMapping("/users/me/followings")
    public List<Favorites>(@RequestParam Long id){


        }
    @GetMapping("/users/me/followers")
    public List<Favorites>(@RequestParam Long id){


    }

     */


}
