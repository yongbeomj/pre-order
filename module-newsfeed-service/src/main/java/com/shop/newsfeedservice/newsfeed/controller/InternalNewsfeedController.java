package com.shop.newsfeedservice.newsfeed.controller;

import com.shop.newsfeedservice.common.response.ResponseDto;
import com.shop.newsfeedservice.newsfeed.dto.request.NewsfeedCreateRequest;
import com.shop.newsfeedservice.newsfeed.service.NewsfeedService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Newsfeeds")
@RestController
@RequestMapping("/api/internal/newsfeeds")
@RequiredArgsConstructor
public class InternalNewsfeedController {

    private final NewsfeedService newsfeedService;

    @Operation(summary = "뉴스피드 생성")
    @PostMapping()
    public ResponseDto<?> createNewsfeed(@RequestBody NewsfeedCreateRequest newsfeedCreateRequest) {
        newsfeedService.createNewsfeed(newsfeedCreateRequest);

        return ResponseDto.success("");
    }

}
