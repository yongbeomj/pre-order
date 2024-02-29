package com.shop.newsfeedservice.newsfeed.service;

import com.shop.newsfeedservice.client.ActivityClient;
import com.shop.newsfeedservice.newsfeed.entity.Newsfeed;
import com.shop.newsfeedservice.newsfeed.dto.request.NewsfeedCreateRequest;
import com.shop.newsfeedservice.newsfeed.repository.NewsfeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final ActivityClient activityClient;

    // 뉴스피드 생성
    public void createNewsfeed(NewsfeedCreateRequest request) {

        Newsfeed newsfeed = Newsfeed.builder()
                .activityUserId(request.getActivityUserId())
                .targetUserId(request.getTargetUserId())
                .targetId(request.getTargetId())
                .newsfeedType(request.getActivityType())
                .build();

        newsfeedRepository.save(newsfeed);
    }

    // 뉴스피드 조회
    public List<Newsfeed> searchNewsfeed(Long principalId) {
        // 내가 팔로우한 전체 유저 조회
        List<Long> toUsers = activityClient.findToUsers(principalId);

        // 팔로우 유저의 히스토리 조회 (뉴스피드 테이블 내역)
        List<Newsfeed> newsfeeds = toUsers.stream()
                .flatMap(toUserId -> newsfeedRepository.findAllByActivityUserId(toUserId).stream())
                .toList();

        return newsfeeds;
    }

}
