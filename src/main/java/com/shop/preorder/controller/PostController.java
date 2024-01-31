package com.shop.preorder.controller;

import com.shop.preorder.domain.Post;
import com.shop.preorder.dto.common.ResponseDto;
import com.shop.preorder.dto.request.PostWriteRequest;
import com.shop.preorder.dto.response.PostWriteResponse;
import com.shop.preorder.service.CustomUserDetailsService;
import com.shop.preorder.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final CustomUserDetailsService userDetailsService;
    private final PostService postService;

    @Operation(summary = "게시글 쓰기")
    @PostMapping("/write")
    public ResponseDto<?> writePosts(@RequestBody PostWriteRequest postWriteRequest, Authentication authentication) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        Post post = postService.writePost(postWriteRequest, userDetails.getUsername());
        return ResponseDto.success(PostWriteResponse.of(post));
    }

    @Operation(summary = "뉴스피드 조회")
    @GetMapping("/newsfeed/{user_id}")
    public void searchNewsfeed() {
    }

    @Operation(summary = "게시글 좋아요 생성")
    @PostMapping("/like/add")
    public void likePosts() {
    }

    @Operation(summary = "게시글별 좋아요 조회")
    @GetMapping("/like/{post_id}")
    public void searchLikeByPosts() {
    }
}
