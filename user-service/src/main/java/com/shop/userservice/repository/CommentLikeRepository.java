package com.shop.userservice.repository;


import com.shop.userservice.domain.Comment;
import com.shop.userservice.domain.CommentLike;
import com.shop.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    boolean existsByCommentAndUser(Comment comment, User user);
}
