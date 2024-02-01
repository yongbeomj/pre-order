package com.shop.preorder.service;

import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.Newsfeed;
import com.shop.preorder.domain.NewsfeedType;
import com.shop.preorder.domain.User;
import com.shop.preorder.exception.BaseException;
import com.shop.preorder.exception.ErrorCode;
import com.shop.preorder.repository.FollowRepository;
import com.shop.preorder.repository.NewsfeedRepository;
import com.shop.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    // 뉴스피드 생성 (저장)
    public void createNewsfeed(Long activityUserId, Long targetUserId, Long targetId, NewsfeedType newsfeedType) {
        User activityUser = userRepository.findById(activityUserId).orElse(null);
        User targetUser = userRepository.findById(targetUserId).orElse(null);

        Newsfeed newsfeed = Newsfeed.builder()
                .activityUser(activityUser)
                .targetUser(targetUser)
                .targetId(targetId)
                .newsfeedType(newsfeedType)
                .build();

        newsfeedRepository.save(newsfeed);
    }

    // 뉴스피드 조회
    public List<Newsfeed> searchNewsfeed(Long fromUserId) {
        List<Follow> fromFollow = followRepository.findAllByFromUserId(fromUserId);

        return fromFollow.stream()
                .map(Follow::getToUser)
                .flatMap(toUser -> newsfeedRepository.findAllByActivityUserId(toUser.getId()).stream()
                        .sorted(Comparator.comparing(Newsfeed::getCreatedAt).reversed()))
                .collect(Collectors.toList());
    }

}
