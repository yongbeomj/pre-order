package com.shop.activityservice.controller;

import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Internal Follow")
@RestController
@RequestMapping("/api/internal")
@RequiredArgsConstructor
public class InternalFollowController {

    private final FollowService followService;

    @Operation(summary = "팔로우 조회")
    @GetMapping("/follows")
    public ResponseEntity<List<Long>> findToUsers(@RequestParam("userId") Long principalId) {
        List<Follow> toUserList = followService.getToUsers(principalId);

        List<Long> followResponses = toUserList.stream()
                .map(Follow::getToUserId)
                .collect(Collectors.toList());

        return ResponseEntity.ok(followResponses);
    }

}
