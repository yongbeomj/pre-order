package com.shop.userservice.repository;

import com.shop.userservice.domain.Post;
import com.shop.userservice.domain.PostLike;
import com.shop.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUser(Post post, User user);
}
