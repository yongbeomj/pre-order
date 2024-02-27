package com.shop.activityservice.comment.service;

import com.shop.activityservice.comment.entiity.Comment;
import com.shop.activityservice.comment.entiity.CommentLike;
import com.shop.activityservice.post.entiity.Post;
import com.shop.activityservice.comment.dto.request.CommentWriteRequest;
import com.shop.activityservice.common.exception.BaseException;
import com.shop.activityservice.common.exception.ErrorCode;
import com.shop.activityservice.comment.repository.CommentLikeRepository;
import com.shop.activityservice.comment.repository.CommentRepository;
import com.shop.activityservice.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    // 댓글 작성
    @Transactional
    public Comment writeComment(CommentWriteRequest commentWriteRequest, Long postId, Long userId) {
        // 포스트 존재 여부 체크
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(ErrorCode.POST_NOT_FOUND));

        return commentRepository.save(commentWriteRequest.toEntity(post, userId));
    }

    // 댓글 좋아요
    @Transactional
    public CommentLike addCommentLike(Long commentId, Long userId) {
        // 댓글 존재 여부 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new BaseException(ErrorCode.COMMENT_NOT_FOUND));

        // 동일 댓글 좋아요 중복 여부 확인
        boolean isCommentLike = commentLikeRepository.existsByCommentIdAndUserId(commentId, userId);
        if (isCommentLike) {
            throw new BaseException(ErrorCode.DUPLICATED_COMMENT_LIKE);
        }

        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .userId(userId)
                .build();

        return commentLikeRepository.save(commentLike);
    }
}
