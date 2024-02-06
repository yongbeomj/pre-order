package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.CommentLike;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.request.CommentWriteRequest;
import com.shop.activityservice.dto.response.CommentLikeResponse;
import com.shop.activityservice.dto.response.CommentWriteResponse;
import com.shop.activityservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comments")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "댓글 작성")
    @PostMapping("/write/{post_id}")
    public ResponseDto<CommentWriteResponse> writeCommentPosts(@PathVariable("post_id") Long postId, @RequestBody CommentWriteRequest commentWriteRequest) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저 정보 가져오기 (유저 서비스)
        Long loginId = null;

        Comment comment = commentService.writeComment(commentWriteRequest, postId, loginId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(comment.newsfeedCreateRequest(loginId));

        return ResponseDto.success(CommentWriteResponse.of(comment));
    }

    @Operation(summary = "댓글 좋아요 추가")
    @GetMapping("/likes/{comment_id}")
    public ResponseDto<CommentLikeResponse> likeComments(@PathVariable("comment_id") Long commentId) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저 정보 가져오기 (유저 서비스)
        Long loginId = null;

        CommentLike commentLike = commentService.addCommentLike(commentId, loginId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(commentLike.newsfeedCreateRequest(loginId));

        return ResponseDto.success(CommentLikeResponse.of(commentLike));
    }

}
