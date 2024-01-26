package com.shop.preorder.controller;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.UserJoinRequest;
import com.shop.preorder.dto.UserJoinResponse;
import com.shop.preorder.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest userJoinRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }

        User user = userService.joinUser(userJoinRequest);
        return ResponseEntity.ok(UserJoinResponse.of(user));
    }

    @Operation(summary = "이메일 인증")
    @PostMapping("/check-mail")
    public void checkMail() {
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public void login() {
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
