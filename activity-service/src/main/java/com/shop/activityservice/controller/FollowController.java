package com.shop.activityservice.controller;

import com.shop.activityservice.domain.CustomUserDetails;
import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.domain.NewsfeedType;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.response.FollowResponse;
import com.shop.activityservice.exception.BaseException;
import com.shop.activityservice.exception.ErrorCode;
import com.shop.activityservice.service.CustomUserDetailsService;
import com.shop.activityservice.service.FollowService;
import com.shop.activityservice.service.NewsfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        // 본인 팔로우 시 예외처리
        if (userDetails.getUser().getId() == toUserId) {
            throw new BaseException(ErrorCode.NOT_FOLLOW_ME);
        }

        Follow follow = followService.followUser(userDetails.getUser().getId(), toUserId);

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), follow.getToUser().getId(), follow.getId(), NewsfeedType.FOLLOW);

        return ResponseDto.success(FollowResponse.of(follow));
    }

}
