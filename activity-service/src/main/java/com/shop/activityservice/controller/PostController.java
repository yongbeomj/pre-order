package com.shop.activityservice.controller;

import com.shop.activityservice.domain.*;
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
