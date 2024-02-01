package com.shop.preorder.controller;

import com.shop.preorder.domain.CustomUserDetails;
import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.NewsfeedType;
import com.shop.preorder.dto.common.ResponseDto;
import com.shop.preorder.dto.response.FollowResponse;
import com.shop.preorder.service.CustomUserDetailsService;
import com.shop.preorder.service.FollowService;
import com.shop.preorder.service.NewsfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final CustomUserDetailsService userDetailsService;
    private final FollowService followService;
    private final NewsfeedService newsfeedService;

    @Operation(summary = "팔로우 하기")
    @PostMapping("/{to_user_id}")
    public ResponseDto<FollowResponse> followUser(@PathVariable("to_user_id") Long toUserId, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        Follow follow = followService.followUser(userDetails.getUser().getId(), toUserId);

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), follow.getToUser().getId(), follow.getId(), NewsfeedType.FOLLOW);

        return ResponseDto.success(FollowResponse.of(follow));
    }

    @Operation(summary = "팔로워 찾기")
    @GetMapping("/find-follower/{user_id}")
    public void findFollower() {
    }

}
