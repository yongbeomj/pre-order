package com.shop.newsfeedservice.repository;

import com.shop.newsfeedservice.domain.Comment;
import com.shop.newsfeedservice.domain.CommentLike;
import com.shop.newsfeedservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentAndUser(Comment comment, User user);
}
