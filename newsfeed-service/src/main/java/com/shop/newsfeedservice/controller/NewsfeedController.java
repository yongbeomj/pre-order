package com.shop.newsfeedservice.controller;

import com.shop.newsfeedservice.client.UserClient;
import com.shop.newsfeedservice.domain.Newsfeed;
import com.shop.newsfeedservice.dto.common.ResponseDto;
import com.shop.newsfeedservice.dto.response.NewsfeedResponse;
import com.shop.newsfeedservice.service.NewsfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

    private final NewsfeedService newsfeedService;
    private final UserClient userClient;

    @Operation(summary = "뉴스피드 조회")
    @GetMapping("/me")
    public ResponseDto<List<NewsfeedResponse>> searchNewsfeed(HttpServletRequest request) {
        // token 유효여부 확인 및 유저 정보 조회
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        Long principalId = userClient.getCurrentUser(header).getBody();
        
        List<Newsfeed> newsfeeds = newsfeedService.searchNewsfeed(principalId);

        List<NewsfeedResponse> newsfeedResponses = newsfeeds.stream()
                .map(newsfeed -> newsfeedService.newsfeedDetails(newsfeed, principalId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return ResponseDto.success(newsfeedResponses);
    }

}
