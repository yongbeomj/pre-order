package com.shop.activityservice.post.repository;

import com.shop.activityservice.post.entiity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
