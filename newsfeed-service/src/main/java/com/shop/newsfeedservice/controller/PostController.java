package com.shop.newsfeedservice.controller;

import com.shop.newsfeedservice.domain.*;
import com.shop.newsfeedservice.dto.common.ResponseDto;
import com.shop.newsfeedservice.dto.request.CommentWriteRequest;
import com.shop.newsfeedservice.dto.request.PostWriteRequest;
import com.shop.newsfeedservice.dto.response.CommentLikeResponse;
import com.shop.newsfeedservice.dto.response.CommentWriteResponse;
import com.shop.newsfeedservice.dto.response.PostLikeResponse;
import com.shop.newsfeedservice.dto.response.PostWriteResponse;
import com.shop.newsfeedservice.service.CustomUserDetailsService;
import com.shop.newsfeedservice.service.NewsfeedService;
import com.shop.newsfeedservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final CustomUserDetailsService userDetailsService;
    private final PostService postService;
    private final NewsfeedService newsfeedService;

    @Operation(summary = "게시글 쓰기")
    @PostMapping("/write")
    public ResponseDto<PostWriteResponse> writePosts(@RequestBody PostWriteRequest postWriteRequest, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        
        Post post = postService.writePost(postWriteRequest, userDetails.getUsername());

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), post.getUser().getId(), post.getId(), NewsfeedType.POST);

        return ResponseDto.success(PostWriteResponse.of(post));
    }

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

    @Operation(summary = "댓글 좋아요 추가")
    @GetMapping("{comment_id}/comment/likes")
    public ResponseDto<CommentLikeResponse> likeComments(@PathVariable("comment_id") Long commentId, Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        CommentLike commentLike = postService.addCommentLike(commentId, userDetails.getUsername());

        // 뉴스피드 생성
        newsfeedService.createNewsfeed(userDetails.getUser().getId(), commentLike.getUser().getId(), commentLike.getId(), NewsfeedType.COMMENT_LIKE);

        return ResponseDto.success(CommentLikeResponse.of(commentLike));
    }

}
