package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
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
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "팔로우 하기")
    @PostMapping("/{to_user_id}")
    public ResponseDto<FollowResponse> followUser(@PathVariable("to_user_id") Long toUserId, HttpServletRequest request) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)
        // TODO : 로그인 유저(fromUserId) 정보 가져오기 (유저 서비스)
        // TODO : 유저 서비스에 header를 넘기는 부분 해결하기
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = header.split(" ")[1].trim();

        Follow follow = followService.followUser(null, toUserId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(follow.newsfeedCreateRequest());

        return ResponseDto.success(FollowResponse.of(follow));
    }

}
