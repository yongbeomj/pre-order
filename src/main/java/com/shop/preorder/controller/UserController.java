package com.shop.preorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Operation(summary = "회원가입")
    @PostMapping("/sign-up")
    public void join() {
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
