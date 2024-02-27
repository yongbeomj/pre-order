package com.shop.activityservice.post.service;

import com.shop.activityservice.post.entiity.Post;
import com.shop.activityservice.post.entiity.PostLike;
import com.shop.activityservice.post.dto.request.PostWriteRequest;
import com.shop.activityservice.common.exception.BaseException;
import com.shop.activityservice.common.response.ErrorCode;
import com.shop.activityservice.post.repository.PostLikeRepository;
import com.shop.activityservice.post.repository.PostRepository;
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
        return postRepository.save(postWriteRequest.toEntity(userId));
    }

    // 포스트 좋아요
    @Transactional
    public PostLike addPostLike(Long postId, Long userId) {
        // 포스트 존재 여부 체크
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));

        // 동일 포스트 좋아요 중복 여부 체크
        boolean isPostLike = postLikeRepository.existsByPostIdAndUserId(postId, userId);
        if (isPostLike) {
            throw new BaseException(ErrorCode.DUPLICATED_POST_LIKE);
        }

        PostLike postLike = PostLike.builder()
                .post(post)
                .userId(userId)
                .build();

        return postLikeRepository.save(postLike);
    }

}
