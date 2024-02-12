package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
import com.shop.activityservice.client.UserClient;
import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.response.FollowResponse;
import com.shop.activityservice.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    private final UserClient userClient;
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "팔로우 추가")
    @PostMapping("/{to_user_id}")
    public ResponseDto<FollowResponse> followUser(@PathVariable("to_user_id") Long toUserId, HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long fromUserId = userClient.getCurrentUser(header).getBody();

        // 팔로우 추가
        Follow follow = followService.followUser(fromUserId, toUserId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(follow.newsfeedCreateRequest());

        return ResponseDto.success(FollowResponse.of(follow));
    }

}
