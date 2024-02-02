package com.shop.activityservice.repository;

import com.shop.activityservice.domain.Comment;
import com.shop.activityservice.domain.CommentLike;
import com.shop.activityservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentAndUser(Comment comment, User user);
}
