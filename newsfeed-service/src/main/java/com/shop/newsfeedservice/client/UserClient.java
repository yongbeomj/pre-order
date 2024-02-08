package com.shop.newsfeedservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${feign.user.name}", url = "${feign.user.url}")
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/current")
    ResponseEntity<Long> getCurrentUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);
}
