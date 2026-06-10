package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.User;
import com.aivle12.book_backend.exception.UserNotFoundException;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor//final 붙는 필드만 받는 생성자
@Service
//여기 이제 각 객체에 대응하도록 imort되었다고 가정할게요


public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    /*
    private final CommetRepository commentRepository;
    private final BookRepository bookRepository;
    private final FavoriteRepository favoriteRepository;

     */
    /*
    프로필 등록
    프로필 수정
    내댓글 조회
    내프로필 조회
    내작품목록 조회
    내즐겨찾기 조회
     */

    public User addUser(User user){
        return userRepository.save(user);
    }

    public User findByEmail(String email){return userRepository.findByEmail(email).orElseThrow(()->
            new IllegalArgumentException("존재하지 않는 유저입니다."));}

    public User updateUser(String email, User user){
        User existing=findByEmail(email);
        if (user.getEmail()!=null){
            existing.setEmail(user.getEmail());
        }
        if (user.getPassword()!=null){
            existing.setPassword(user.getPassword());
        }
        if (user.getNickname()!=null){
            existing.setNickname(user.getNickname());
        }
        //id와 created_at은 애초에 수정이 불가할 것으로 보임

        return userRepository.save(existing);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(String email){
        if (userRepository.existsByEmail(email)){
            userRepository.deleteByEmail(email);
        }else{
            throw new UserNotFoundException(email);
        }
    }





    /*
    public List<Comment> userComment(User user){
        return commentRepository.findbyUser(user)
    }

    public List<Books> userBooks(User user){
        return bookRepository.findbyUser(user);
    }

    public List<Books> userFavorite{
        return favoriteRepository.findbyUser(user);
        }

     */





}