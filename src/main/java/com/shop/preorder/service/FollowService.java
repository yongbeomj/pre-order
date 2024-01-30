package com.shop.preorder.service;

import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.User;
import com.shop.preorder.repository.FollowRepository;
import com.shop.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public void followUser(Long fromUserid, Long toUserId) {
        User fromUser = userRepository.findById(fromUserid).orElseThrow();
        User toUser = userRepository.findById(toUserId).orElseThrow();

        // validation
        followRepository.findByFromUserAndToUser(fromUser, toUser).ifPresent(it -> {
            throw new IllegalArgumentException("이미 해당 사용자를 팔로우하고 있습니다.");
        });

        Follow follow = Follow.builder()
                .fromUser(fromUser)
                .toUser(toUser)
                .build();

        followRepository.save(follow);
    }
}
