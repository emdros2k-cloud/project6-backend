package com.aivle12.book_backend.controller;

import com.aivle12.book_backend.domain.User;
import com.aivle12.book_backend.repository.UserRepository;
import com.aivle12.book_backend.service.UserService;
import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

public class UserController{
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/users/{email}")
    public Optional<User> getUser(@PathVariable String email){
        return userRepository.findByEmail(email);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PatchMapping("/users/{email}")
    public User updateUser(@PathVariable String email, @RequestBody User user){
        return userService.updateUser(email,user);
    }

    @GetMapping("/users")
    public List<User> allProfile() {return userService.findAll();
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<Profile> deleteUser(@PathVariable String email){
        userService.delete(email);
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
    public List<Favorites>(@RequestParam Long id){


    @GetMapping("/users/me/followings")
    public List<Favorites>(@RequestParam Long id){


        }
    @GetMapping("/users/me/followers")
    public List<Favorites>(@RequestParam Long id){


    }

     */




}