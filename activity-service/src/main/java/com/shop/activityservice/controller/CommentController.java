package com.shop.activityservice.controller;

import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.CommentLike;
import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.PostLike;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.request.CommentWriteRequest;
import com.shop.activityservice.dto.request.PostWriteRequest;
import com.shop.activityservice.dto.response.CommentLikeResponse;
import com.shop.activityservice.dto.response.CommentWriteResponse;
import com.shop.activityservice.dto.response.PostLikeResponse;
import com.shop.activityservice.dto.response.PostWriteResponse;
import com.shop.activityservice.service.CommentService;
import com.shop.activityservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comments")
@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/write/{post_id}")
    public ResponseDto<CommentWriteResponse> writeCommentPosts(@PathVariable("post_id") Long postId, @RequestBody CommentWriteRequest commentWriteRequest) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저 정보 가져오기 (유저 서비스)

        Comment comment = commentService.writeComment(commentWriteRequest, postId, null);

        // TODO : 뉴스피드 생성 이벤트 호출 (뉴스피드 서비스)

        return ResponseDto.success(CommentWriteResponse.of(comment));
    }

    @Operation(summary = "게시글 좋아요 추가")
    @GetMapping("/likes/{post_id}")
    public ResponseDto<PostLikeResponse> likePosts(@PathVariable("post_id") Long postId, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        PostLike postLike = postService.addPostLike(postId, userDetails.getUsername());

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), postLike.getUser().getId(), postLike.getId(), NewsfeedType.POST_LIKE);

        return ResponseDto.success(PostLikeResponse.of(postLike));
    }

}
