package com.shop.preorder.repository;

import com.shop.preorder.domain.Post;
import com.shop.preorder.domain.PostLike;
import com.shop.preorder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUser(Post post, User user);
}
