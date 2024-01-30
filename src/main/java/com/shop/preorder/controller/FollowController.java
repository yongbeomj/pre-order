package com.shop.preorder.controller;

import com.shop.preorder.domain.CustomUserDetails;
import com.shop.preorder.service.CustomUserDetailsService;
import com.shop.preorder.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final CustomUserDetailsService userDetailsService;
    private final FollowService followService;

    @Operation(summary = "팔로우 하기")
    @PostMapping("/{to_user_id}")
    public ResponseEntity<?> followUser(@PathVariable("to_user_id") Long toUserId, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        followService.followUser(userDetails.getUser().getId(), toUserId);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "팔로워 찾기")
    @GetMapping("/find-follower/{user_id}")
    public void findFollower() {
    }

}
