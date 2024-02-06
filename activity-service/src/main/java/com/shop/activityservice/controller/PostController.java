package com.shop.activityservice.controller;

import com.shop.activityservice.client.NewsfeedClient;
import com.shop.activityservice.domain.Post;
import com.shop.activityservice.domain.PostLike;
import com.shop.activityservice.dto.common.ResponseDto;
import com.shop.activityservice.dto.request.PostWriteRequest;
import com.shop.activityservice.dto.response.PostLikeResponse;
import com.shop.activityservice.dto.response.PostWriteResponse;
import com.shop.activityservice.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final NewsfeedClient newsfeedClient;

    @Operation(summary = "포스트 작성")
    @PostMapping("/write")
    public ResponseDto<PostWriteResponse> writePosts(@RequestBody PostWriteRequest postWriteRequest) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저 정보 가져오기 (유저 서비스)
        Long loginId = null;

        Post post = postService.writePost(postWriteRequest, loginId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(post.newsfeedCreateRequest(loginId));

        return ResponseDto.success(PostWriteResponse.of(post));
    }

    @Operation(summary = "포스트 좋아요")
    @GetMapping("/likes/{post_id}")
    public ResponseDto<PostLikeResponse> likePosts(@PathVariable("post_id") Long postId) {
        // TODO : 인증여부 체크 validation 실행 (유저 서비스)

        // TODO : 로그인 유저 정보 가져오기 (유저 서비스)
        Long loginId = null;

        PostLike postLike = postService.addPostLike(postId, loginId);

        // 뉴스피드 생성
        newsfeedClient.createNewsfeed(postLike.newsfeedCreateRequest(loginId));

        return ResponseDto.success(PostLikeResponse.of(postLike));
    }

}
