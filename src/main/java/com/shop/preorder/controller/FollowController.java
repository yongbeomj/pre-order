package com.shop.preorder.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Follow")
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Operation(summary = "팔로우 하기")
    @PostMapping("/add")
    public void addFollow() {
    }

    @Operation(summary = "팔로워 찾기")
    @GetMapping("/find-follower/{user_id}")
    public void findFollower() {
    }

}
