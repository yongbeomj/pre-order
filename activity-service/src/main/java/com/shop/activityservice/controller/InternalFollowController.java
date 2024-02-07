package com.shop.activityservice.controller;

import com.shop.activityservice.domain.CommentLike;
import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.PostLike;
import com.shop.activityservice.dto.request.NewsfeedSearchRequest;
import com.shop.activityservice.dto.response.*;
import com.shop.activityservice.service.CommentService;
import com.shop.activityservice.service.FollowService;
import com.shop.activityservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Internal Follow")
@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InternalFollowController {

    private final FollowService followService;
    private final PostService postService;
    private final CommentService commentService;

    @Operation(summary = "팔로우 조회")
    @GetMapping("/follows")
    public ResponseEntity<List<Long>> findToUsers(@RequestParam("userId") Long principalId) {
        List<Follow> toUserList = followService.getToUsers(principalId);

        List<Long> followResponses = toUserList.stream()
                .map(Follow::getToUserId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(followResponses);
    }

    @Operation(summary = "팔로우 유저 상세정보 조회")
    @GetMapping("/follows/details")
    public ResponseEntity<List<?>> searchActivityDetails(List<NewsfeedSearchRequest> searchRequests) {
        // 리스트로 request 받아온다
        // 반복문으로 하나씩 확인한다
        // 타입에 맞춰서 findbyid로 정보 꺼내온다
        // 꺼내온 정보를 newsfeedresponse에 담는다
        //
        for (NewsfeedSearchRequest searchRequest : searchRequests) {
            Object searchInfo = null;
            switch (searchRequest.getActivityType()) {
                case "FOLLOW":
                    searchInfo = followService.findFollow(searchRequest.getTargetId());
                    break;
                case "POST":
                    searchInfo = postService.findPost(searchRequest.getTargetId());
                    break;
                case "POST_LIKE":
                    searchInfo = postService.findPostLike(searchRequest.getTargetId());
                    break;
                case "COMMENT":
                    searchInfo = commentService.findComment(searchRequest.getTargetId());
                    break;
                case "COMMENT_LIKE":
                    searchInfo = commentService.findCommentLike(searchRequest.getTargetId());
                    break;
            }
        }


    }

}
