package com.shop.activityservice.service;

import com.shop.activityservice.domain.*;
import com.shop.activityservice.dto.request.CommentWriteRequest;
import com.shop.activityservice.dto.request.PostWriteRequest;
import com.shop.activityservice.exception.BaseException;
import com.shop.activityservice.exception.ErrorCode;
import com.shop.activityservice.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;

    // 포스트 작성
    @Transactional
    public Post writePost(PostWriteRequest postWriteRequest, Long userId) {
        return postRepository.save(postWriteRequest.toEntity(Long userId));
    }

    @Transactional
    public PostLike addPostLike(Long postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));

        User writer = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        boolean isPostLike = postLikeRepository.existsByPostAndUser(post, writer);
        if (isPostLike) {
            throw new BaseException(ErrorCode.DUPLICATED_POST_LIKE);
        }

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(writer)
                .build();

        return postLikeRepository.save(postLike);
    }

}
