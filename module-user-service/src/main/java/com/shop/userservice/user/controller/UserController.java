package com.shop.userservice.user.controller;

import com.shop.userservice.common.exception.BaseException;
import com.shop.userservice.common.response.ErrorCode;
import com.shop.userservice.common.response.ResponseDto;
import com.shop.userservice.user.entity.User;
import com.shop.userservice.user.dto.request.*;
import com.shop.userservice.user.dto.response.*;
import com.shop.userservice.user.service.CustomUserDetailsService;
import com.shop.userservice.user.service.MailService;
import com.shop.userservice.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
    public ResponseDto<UserJoinResponse> join(@Valid @RequestBody UserJoinRequest userJoinRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        // 인증 번호 일치 여부
        boolean isAuth = mailService.isAuthNumber(userJoinRequest.getEmail(), userJoinRequest.getAutoNumber());
        if (!isAuth) {
            throw new BaseException(ErrorCode.INVALID_AUTH_CODE);
        }

        // 회원 가입
        User user = userService.joinUser(userJoinRequest);
        return ResponseDto.success(UserJoinResponse.of(user));
    }

    @Operation(summary = "이메일 인증")
    @PostMapping("/join/email-check")
    public ResponseDto<EmailCheckResponse> checkMail(@Valid @RequestBody EmailCheckRequest emailCheckRequest, BindingResult result) {
        if (result.hasErrors()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        int authCode = mailService.sendEmail(emailCheckRequest.getEmail());
        return ResponseDto.success(EmailCheckResponse.of(authCode));
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public ResponseDto<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest, BindingResult result, HttpServletResponse response) {
        if (result.hasErrors()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        String accessToken = userService.login(userLoginRequest);
        return ResponseDto.success(UserLoginResponse.of(accessToken));
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public ResponseDto<TokenResponse> logout(HttpServletRequest request, Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.split(" ")[1].trim();

        userService.logout(token);
        return ResponseDto.success(TokenResponse.of(userDetails.getUsername()));
    }

    @Operation(summary = "모든 기기에서 로그아웃")
    @GetMapping("/all-logout")
    public ResponseDto<TokenResponse> allDevicelogout(Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        userService.allLogout(userDetails.getUsername());

        return ResponseDto.success(TokenResponse.of(userDetails.getUsername()));
    }


    @Operation(summary = "회원정보 수정")
    @PutMapping("/modify")
    public ResponseDto<UserModifyResponse> modifyProfile(@RequestBody UserModifyRequest userModifyRequest, Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        User updateUser = userService.modifyProfile(userModifyRequest, userDetails.getUsername());

        return ResponseDto.success(UserModifyResponse.of(updateUser));
    }

    @Operation(summary = "회원 비밀번호 수정")
    @PutMapping("/pw-modify")
    public ResponseDto<UserPwModifyResponse> modifyPassword(@Valid @RequestBody UserPwModifyRequest userPwModifyRequest, Authentication authentication, BindingResult result) {
        if (result.hasErrors()) {
            throw new BaseException(ErrorCode.INVALID_REQUEST);
        }

        // 인증 정보로 유저 정보 추출
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        User updateUser = userService.modifyPassword(userPwModifyRequest, userDetails.getUsername());

        // 모든 기기에서 로그아웃
        userService.allLogout(userDetails.getUsername());
        return ResponseDto.success(UserPwModifyResponse.of(updateUser));
    }

}
