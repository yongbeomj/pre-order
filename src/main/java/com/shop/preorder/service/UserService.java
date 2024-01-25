package com.shop.preorder.service;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.UserJoinRequest;
import com.shop.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // 회원가입
    @Transactional
    public User joinUser(UserJoinRequest userJoinRequest) {
        // validation
        // email 인증
        // password 암호화
        // 데이터 저장
        return userRepository.save(userJoinRequest.toEntity());
    }

}
