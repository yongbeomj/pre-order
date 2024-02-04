package com.shop.activityservice.controller;

import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.response.FollowResponse;
import com.shop.activityservice.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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

    @Operation(summary = "팔로우 하기")
    @PostMapping("/{to_user_id}")
    public ResponseDto<FollowResponse> followUser(@PathVariable("to_user_id") Long toUserId) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저(fromUserId) 정보 가져오기 (유저 서비스)
        Long fromUserId = null;
        Follow follow = followService.followUser(null, toUserId);

        // TODO : 뉴스피드 생성 이벤트 호출 (뉴스피드 서비스)

        return ResponseDto.success(FollowResponse.of(follow));
    }

}
