package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
import com.shop.activityservice.client.UserClient;
import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.PostLike;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.request.PostWriteRequest;
import com.shop.activityservice.dto.response.PostLikeResponse;
import com.shop.activityservice.dto.response.PostWriteResponse;
import com.shop.activityservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserClient userClient;
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "포스트 작성")
    @PostMapping("/write")
    public ResponseDto<PostWriteResponse> writePosts(@RequestBody PostWriteRequest postWriteRequest, HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long principalId = userClient.getCurrentUser(header).getBody();

        // 포스트 작성
        Post post = postService.writePost(postWriteRequest, principalId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(post.newsfeedCreateRequest(principalId));

        return ResponseDto.success(PostWriteResponse.of(post));
    }

    @Operation(summary = "포스트 좋아요")
    @GetMapping("/likes/{post_id}")
    public ResponseDto<PostLikeResponse> likePosts(@PathVariable("post_id") Long postId, HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long principalId = userClient.getCurrentUser(header).getBody();

        // 포스트 좋아요
        PostLike postLike = postService.addPostLike(postId, principalId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(postLike.newsfeedCreateRequest(principalId));

        return ResponseDto.success(PostLikeResponse.of(postLike));
    }

}
