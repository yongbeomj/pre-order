package com.shop.newsfeedservice.controller;

import com.shop.newsfeedservice.domain.CustomUserDetails;
import com.shop.newsfeedservice.domain.Newsfeed;
import com.shop.newsfeedservice.dto.common.ResponseDto;
import com.shop.newsfeedservice.dto.response.NewsfeedResponse;
import com.shop.newsfeedservice.service.CustomUserDetailsService;
import com.shop.newsfeedservice.service.NewsfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Tag(name = "Newsfeeds")
@RestController
@RequestMapping("/api/newsfeeds")
@RequiredArgsConstructor
public class NewsfeedController {

    private final CustomUserDetailsService userDetailsService;
    private final NewsfeedService newsfeedService;

    @Operation(summary = "뉴스피드 조회")
    @GetMapping("/me")
    public ResponseDto<List<NewsfeedResponse>> searchNewsfeed(Authentication authentication) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        List<Newsfeed> newsfeeds = newsfeedService.searchNewsfeed(userDetails.getUser().getId());

        List<NewsfeedResponse> newsfeedResponses = newsfeeds.stream()
                .map(newsfeed -> newsfeedService.newsfeedDetails(newsfeed, userDetails.getUser().getId()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ResponseDto.success(newsfeedResponses);
    }

}
