package com.shop.preorder.controller;

import com.shop.preorder.domain.User;
import com.shop.preorder.dto.*;
import com.shop.preorder.service.CustomUserDetailsService;
import com.shop.preorder.service.MailService;
import com.shop.preorder.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Users")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
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
    public ResponseEntity<?> login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) {
        String accessToken = userService.login(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        Cookie cookie = new Cookie("access_token", accessToken);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        response.addCookie(cookie);

        return ResponseEntity.ok(accessToken);
    }

//    @Operation(summary = "로그아웃")
//    @GetMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
//        // 클라이언트에서 요청된 모든 쿠키 가져오기
//        Cookie[] cookies = request.getCookies();
//        String token = "";
//
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("access_token")) {
//                    token = cookie.getValue();
//                    userService.logout(token);
//                    // 토큰이 저장된 쿠키를 삭제하기 위해 유효 시간을 0으로 설정
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
//
//        userService.logout(token);
//
//        return ResponseEntity.ok("ok");
//    }

    @Operation(summary = "회원정보 수정")
    @PutMapping("/modify")
    public ResponseEntity<UserModifyResponse> modifyProfile(@RequestBody UserModifyRequest userModifyRequest, Authentication authentication) {
        // 인증 정보로 유저 정보 추출
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        User updateUser = userService.modifyProfile(userModifyRequest, userDetails.getUsername());
        return ResponseEntity.ok(UserModifyResponse.of(updateUser));
    }

    @Operation(summary = "회원 비밀번호 수정")
    @PutMapping("/pw-modify")
    public ResponseEntity<UserPwModifyResponse> modifyPassword(@Valid @RequestBody UserPwModifyRequest userPwModifyRequest, Authentication authentication, BindingResult result) {
        // 입력 비밀번호 validation
        if (result.hasErrors()) {
            throw new IllegalArgumentException();
        }

        // 인증 정보로 유저 정보 추출
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        User updateUser = userService.modifyPassword(userPwModifyRequest, userDetails.getUsername());
        return ResponseEntity.ok(UserPwModifyResponse.of(updateUser));
    }

}
