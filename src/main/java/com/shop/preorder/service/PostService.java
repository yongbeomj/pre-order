package com.shop.preorder.service;

import com.shop.preorder.domain.Comment;
import com.shop.preorder.domain.Post;
import com.shop.preorder.domain.User;
import com.shop.preorder.dto.request.CommentWriteRequest;
import com.shop.preorder.dto.request.PostWriteRequest;
import com.shop.preorder.exception.BaseException;
import com.shop.preorder.exception.ErrorCode;
import com.shop.preorder.repository.CommentRepository;
import com.shop.preorder.repository.PostRepository;
import com.shop.preorder.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

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
}
