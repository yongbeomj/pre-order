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

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;

    // 포스트 작성
    @Transactional
    public Post writePost(PostWriteRequest postWriteRequest, String email) {

        User writer = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return postRepository.save(postWriteRequest.toEntity(writer));
    }

    // 댓글 작성
    @Transactional
    public Comment writeComment(CommentWriteRequest commentWriteRequest, Long postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));

        User writer = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        return commentRepository.save(commentWriteRequest.toEntity(post, writer));
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

    @Transactional
    public CommentLike addCommentLike(Long commentId, String email) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));

        User writer = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));

        boolean isCommentLike = commentLikeRepository.existsByCommentAndUser(comment, writer);
        if (isCommentLike) {
            throw new BaseException(ErrorCode.DUPLICATED_COMMENT_LIKE);
        }

        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(writer)
                .build();

        return commentLikeRepository.save(commentLike);
    }
}
