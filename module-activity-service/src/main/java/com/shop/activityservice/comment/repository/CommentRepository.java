package com.shop.activityservice.comment.repository;

import com.shop.activityservice.comment.entiity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
