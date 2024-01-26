package com.shop.preorder.service;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.UserJoinRequest;
import com.shop.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입
    @Transactional
    public User joinUser(UserJoinRequest userJoinRequest) {
        // validation - 이메일 중복 여부
        duplicateEmail(userJoinRequest.getEmail());

        // email 인증

        // password 암호화
        String password = passwordEncoder.encode(userJoinRequest.getPassword());
        userJoinRequest.setPassword(password);

        // 데이터 저장
        return userRepository.save(userJoinRequest.toEntity());
    }

    // 이메일 중복 여부
    public void duplicateEmail(String email) {
        userRepository.findByEmail(email).ifPresent(it -> {
            throw new IllegalStateException("이메일 중복");
        });
    }

}
