package com.shop.preorder.service;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.UserJoinRequest;
import com.shop.preorder.dto.UserModifyRequest;
import com.shop.preorder.repository.UserRepository;
import com.shop.preorder.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration-in-second}")
    private Long expiredTimeMs;

    // 회원가입
    @Transactional
    public User joinUser(UserJoinRequest userJoinRequest) {
        // validation - 이메일 중복 여부
        duplicateEmail(userJoinRequest.getEmail());

        // password 암호화
        String password = passwordEncoder.encode(userJoinRequest.getPassword());
        userJoinRequest.setPassword(password);

        // 데이터 저장
        return userRepository.save(userJoinRequest.toEntity());
    }

    // 이메일 중복 여부
    public void duplicateEmail(String email) {
        userRepository.findByEmail(email).ifPresent(it -> {
            throw new IllegalArgumentException("이메일 중복");
        });
    }

    // 로그인
    public String login(String email, String password) {
//        // 회원가입 여부 체크
//        User savedUser = userRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
//
//        // 비밀번호 체크
//        if (!passwordEncoder.matches(password, savedUser.getPassword())) {
//            throw new IllegalArgumentException("비밀번호 일치하지 않음");
//        }

        return JwtTokenUtil.createToken(email, secretKey, expiredTimeMs);
    }

    // 프로필 수정
    @Transactional
    public User modifyProfile(UserModifyRequest userModifyRequest, String email) {
        User findUser = userRepository.findByEmail(email).orElseThrow();

        if (isNullOrEmptyFields(userModifyRequest.getName())) {
            findUser.setName(userModifyRequest.getName());
        }

        if (isNullOrEmptyFields(userModifyRequest.getProfileImage())) {
            findUser.setProfileImage(userModifyRequest.getProfileImage());
        }

        if (isNullOrEmptyFields(userModifyRequest.getGreeting())) {
            findUser.setGreeting(userModifyRequest.getGreeting());
        }

        return userRepository.saveAndFlush(findUser);
    }

    // 입력 필드 빈 값 체크
    public static boolean isNullOrEmptyFields(String value) {
        return StringUtils.hasText(value);
    }

    // 로그아웃
    public void logout(String token) {
    }


}
