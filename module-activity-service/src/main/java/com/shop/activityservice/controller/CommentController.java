package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
import com.shop.activityservice.client.UserClient;
import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.CommentLike;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.request.CommentWriteRequest;
import com.shop.activityservice.dto.response.CommentLikeResponse;
import com.shop.activityservice.dto.response.CommentWriteResponse;
import com.shop.activityservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comments")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserClient userClient;
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "댓글 작성")
    @PostMapping("/write/{post_id}")
    public ResponseDto<CommentWriteResponse> writeCommentPosts(@PathVariable("post_id") Long postId, @RequestBody CommentWriteRequest commentWriteRequest, HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long principalId = userClient.getCurrentUser(header).getBody();

        // 댓글 작성
        Comment comment = commentService.writeComment(commentWriteRequest, postId, principalId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(comment.newsfeedCreateRequest(principalId));

        return ResponseDto.success(CommentWriteResponse.of(comment));
    }

    @Operation(summary = "댓글 좋아요 추가")
    @GetMapping("/likes/{comment_id}")
    public ResponseDto<CommentLikeResponse> likeComments(@PathVariable("comment_id") Long commentId, HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long principalId = userClient.getCurrentUser(header).getBody();

        // 댓글 좋아요
        CommentLike commentLike = commentService.addCommentLike(commentId, principalId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(commentLike.newsfeedCreateRequest(principalId));

        return ResponseDto.success(CommentLikeResponse.of(commentLike));
    }

}
