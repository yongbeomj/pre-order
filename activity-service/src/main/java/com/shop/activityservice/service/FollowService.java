package com.shop.activityservice.service;

import com.shop.activityservice.domain.Follow;
import com.shop.activityservice.exception.BaseException;
import com.shop.activityservice.exception.ErrorCode;
import com.shop.activityservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public Follow followUser(Long fromUserid, Long toUserId) {
        // 본인 팔로우 하는 경우 체크
        if (fromUserid == toUserId) {
            throw new BaseException(ErrorCode.NOT_FOLLOW_ME);
        }

        // 팔로우 중복 체크
        followRepository.findByFromUserIdAndToUserId(fromUserid, toUserId).ifPresent(it -> {
            throw new BaseException(ErrorCode.ALREADY_USER_FOLLOW);
        });

        Follow follow = Follow.builder()
                .fromUserId(fromUserid)
                .toUserId(toUserId)
                .build();

        return followRepository.save(follow);
    }
}
