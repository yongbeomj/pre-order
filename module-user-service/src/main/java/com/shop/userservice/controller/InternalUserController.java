package com.shop.userservice.controller;

import com.shop.userservice.domain.CustomUserDetails;
import com.shop.userservice.service.CustomUserDetailsService;
import com.shop.userservice.util.JwtTokenUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Internal Users")
@RestController
@RequestMapping("/api/internal/users")
@RequiredArgsConstructor
public class InternalUserController {

    @Value("${jwt.secret}")
    private String secretKey;

    private final CustomUserDetailsService userDetailsService;


    @Operation(summary = "유저 정보 찾기")
    @GetMapping("/current")
    public ResponseEntity<Long> getCurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String header) {
        String token = header.split(" ")[1].trim();
        String userName = JwtTokenUtil.getUserName(token, secretKey);

        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return ResponseEntity.ok(userDetails.getUser().getId());
    }

}
