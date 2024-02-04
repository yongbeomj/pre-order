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
import com.shop.activityservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;

    @Operation(summary = "댓글 작성")
    @PostMapping("/{post_id}/comment")
    public ResponseDto<CommentWriteResponse> writeCommentPosts(@PathVariable("post_id") Long postid, @RequestBody CommentWriteRequest commentWriteRequest, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        Comment comment = postService.writeComment(commentWriteRequest, postid, userDetails.getUsername());

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), comment.getUser().getId(), comment.getId(), NewsfeedType.COMMENT);

        return ResponseDto.success(CommentWriteResponse.of(comment));
    }

    @Operation(summary = "게시글 좋아요 추가")
    @GetMapping("{post_id}/likes")
    public ResponseDto<PostLikeResponse> likePosts(@PathVariable("post_id") Long postId, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        PostLike postLike = postService.addPostLike(postId, userDetails.getUsername());

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), postLike.getUser().getId(), postLike.getId(), NewsfeedType.POST_LIKE);

        return ResponseDto.success(PostLikeResponse.of(postLike));
    }

}
