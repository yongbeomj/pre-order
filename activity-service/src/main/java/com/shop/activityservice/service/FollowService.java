package com.shop.activityservice.service;

import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.domain.User;
import com.shop.activityservice.exception.BaseException;
import com.shop.activityservice.exception.ErrorCode;
import com.shop.activityservice.repository.FollowRepository;
import com.shop.activityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public Follow followUser(Long fromUserid, Long toUserId) {
        User fromUser = userRepository.findById(fromUserid).orElseThrow();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        // validation
        followRepository.findByFromUserAndToUser(fromUser, toUser).ifPresent(it -> {
            throw new BaseException(ErrorCode.ALREADY_USER_FOLLOW);
        });

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        return followRepository.save(follow);
    }
}