package com.aivle12.book_backend.service;

import com.aivle12.book_backend.domain.Profile;
import com.aivle12.book_backend.exception.ProfileNotFoundException;
import com.aivle12.book_backend.repository.ProfileRepository;
import com.aivle12.book_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import com.aivle12.book_backend.repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor//final 붙는 필드만 받는 생성자
@Service

public class ProfileService {
    private final ProfileRepository profileRepository;

    /*
    회원가입
    로그인
    로그아웃
    마이페이지
    닉네임 중복 확인
    이메일 중복 확인

    이 아닌 거 같은데... 일단 다시 확인
     */

    public Profile searchByEmail(String email){
        return profileRepository.findByEmail(email).orElseThrow(()->
                new RuntimeException(email+" 없음"));
    }

    public Profile searchById(Long id){
        return profileRepository.findById(id).orElseThrow(()->
                new ProfileNotFoundException(id));

    }


    public Profile create(Profile profile){
        return profileRepository.save(profile);
    }

    public Profile update(Long id, Profile profile){
        Profile existing=searchById(id);

        if(profile.getBio()!=null){
            existing.setBio(profile.getBio());
        }
        if(profile.getAvatar()!=null){
            existing.setAvatar(profile.getAvatar());
        }//이제 existing은 업데이트된 정보를 가지게 된다

        return profileRepository.save(existing);
    }

    public void delete(Long id){
        if (profileRepository.existsById(id)){
            profileRepository.deleteById(id);
        }else{
            throw new ProfileNotFoundException(id);
        }
    }


    public List<Profile> findAll() {
        return profileRepository.findAll();
    }
}