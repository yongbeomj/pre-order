package com.shop.preorder.service;

import com.shop.preorder.domain.Newsfeed;
import com.shop.preorder.domain.NewsfeedType;
import com.shop.preorder.repository.NewsfeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;

    // 뉴스피드 생성 (저장)
    public void createNewsfeed(Long fromUserId, Long activityId, NewsfeedType newsfeedType) {
        Newsfeed newsfeed = Newsfeed.builder()
                .userId(fromUserId)
                .activityId(activityId)
                .newsfeedType(newsfeedType)
                .build();

        newsfeedRepository.save(newsfeed);
    }
}
