package com.shop.newsfeedservice.client;

import com.shop.newsfeedservice.dto.request.NewsfeedSearchRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${feign.activity.name}", url = "${feign.activity.url}")
public interface ActivityClient {

    // 내가 팔로우한 유저 리스트 조회
    @RequestMapping(method = RequestMethod.GET, value = "/follows", consumes = "application/json")
    List<Long> findToUsers(@RequestParam(name = "userId") Long principalId);

    // 나를 팔로우한 유저 리스트 조회
    @RequestMapping(method = RequestMethod.GET, value = "/followers", consumes = "application/json")
    List<Long> findFromUsers(@RequestParam(name = "userId") Long principalId);

    // 내가 팔로우한 유저 활동 상세 내역 조회 (뉴스피드)
    @RequestMapping(method = RequestMethod.GET, value = "/follows/details", consumes = "application/json")
    List<Long> newsfeedDetails(@RequestBody List<NewsfeedSearchRequest> searchRequests);


}
