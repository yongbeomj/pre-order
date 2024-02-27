package com.shop.activityservice.follow.service;

import com.shop.activityservice.follow.entiity.Follow;
import com.shop.activityservice.common.exception.BaseException;
import com.shop.activityservice.common.response.ErrorCode;
import com.shop.activityservice.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    // 팔로우 생성
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

    // 팔로우 유저 조회
    public List<Follow> getToUsers(Long fromUserId) {
        return followRepository.findAllByFromUserId(fromUserId);
    }

}

