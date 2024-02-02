package com.shop.newsfeedservice.repository;

import com.shop.newsfeedservice.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
