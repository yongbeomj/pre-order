package com.shop.activityservice.repository;


import com.shop.activityservice.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUserId(Long postId, Long userId);
}
