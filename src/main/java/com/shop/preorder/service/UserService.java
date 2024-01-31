package com.shop.preorder.service;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.common.ErrorResponse;
import com.shop.preorder.dto.common.ResponseDto;
import com.shop.preorder.dto.request.UserJoinRequest;
import com.shop.preorder.dto.request.UserLoginRequest;
import com.shop.preorder.dto.request.UserModifyRequest;
import com.shop.preorder.dto.request.UserPwModifyRequest;
import com.shop.preorder.exception.BaseException;
import com.shop.preorder.exception.ErrorCode;
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
            throw new BaseException(ErrorCode.DUPLICATED_USER_EMAIL);
        });
    }

    // 로그인
    public String login(UserLoginRequest userLoginRequest) {
        // 회원가입 여부 체크
        User savedUser = userRepository.findByEmail(userLoginRequest.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 체크
//        if (!passwordEncoder.matches(userLoginRequest.getPassword(), savedUser.getPassword())) {
//            throw new BaseException(ErrorCode.INVALID_PASSWORD);
//        }

        return JwtTokenUtil.createToken(userLoginRequest.getEmail(), secretKey, expiredTimeMs);
    }

    // 프로필 수정
    @Transactional
    public User modifyProfile(UserModifyRequest userModifyRequest, String email) {
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

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

    @Transactional
    public User modifyPassword(UserPwModifyRequest userPwModifyRequest, String email) {
        User findUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        // 이전 비밀번호 일치 여부
        if (!passwordEncoder.matches(userPwModifyRequest.getPrevPassword(), findUser.getPassword())) {
            throw new BaseException(ErrorCode.INVALID_PASSWORD);
        }

        // password 암호화
        String updatePassword = passwordEncoder.encode(userPwModifyRequest.getNewPassword());
        findUser.setPassword(updatePassword);

        return userRepository.saveAndFlush(findUser);
    }

    // 로그아웃
    public void logout(String token) {
    }


}
