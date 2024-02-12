package com.shop.activityservice.client;

import com.shop.activityservice.dto.request.NewsfeedCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.newsfeed.name}", url = "${feign.newsfeed.url}")
public interface NewsfeedClient {
    // 뉴스피드 생성
    @RequestMapping(method = RequestMethod.POST, value = "/newsfeeds")
    void createNewsfeed(NewsfeedCreateRequest newsfeedCreateRequest);
}
