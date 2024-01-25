package com.shop.preorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Posts")
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Operation(summary = "게시글 쓰기")
    @GetMapping("/write")
    public void writePosts() {
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
