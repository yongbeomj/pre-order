package com.shop.preorder.controller;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.*;
import com.shop.preorder.service.MailService;
import com.shop.preorder.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MailService mailService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest userJoinRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }

        // 인증 번호 일치 여부
        boolean isAuth = mailService.isAuthNumber(userJoinRequest.getEmail(), userJoinRequest.getAutoNumber());
        if (!isAuth) {
            throw new IllegalArgumentException();
        }

        // 회원 가입
        User user = userService.joinUser(userJoinRequest);
        return ResponseEntity.ok(UserJoinResponse.of(user));
    }

    @Operation(summary = "이메일 인증")
    @PostMapping("/join/email-check")
    public ResponseEntity<?> checkMail(@Valid @RequestBody EmailCheckRequest emailCheckRequest) {
        int result = mailService.sendEmail(emailCheckRequest.getEmail());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest) {
        String token = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public void logout() {
    }

    @Operation(summary = "유저 프로필 조회")
    @GetMapping("/{user_id}")
    public void searchUserProfile() {
    }

    @Operation(summary = "프로필 수정")
    @PutMapping("/{user_id}")
    public void updatedUserProfile() {
    }

}
