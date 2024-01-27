package com.shop.preorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Reviews")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Operation(summary = "댓글 작성")
    @PostMapping("/write")
    public void writeReview() {
    }

    @Operation(summary = "게시글 별 댓글 조회")
    @GetMapping("/{post_id}")
    public void searchReview() {
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{review_id}")
    public void updatedReview() {
    }

    @Operation(summary = "댓글 좋아요 생성")
    @PostMapping("/like/add")
    public void likeReview() {
    }

    @Operation(summary = "댓글별 좋아요 조회")
    @GetMapping("/like/{review_id}")
    public void searchLikeByReview() {
    }
}
