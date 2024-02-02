package com.shop.newsfeedservice.repository;


import com.shop.newsfeedservice.domain.Post;
import com.shop.newsfeedservice.domain.PostLike;
import com.shop.newsfeedservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    boolean existsByPostAndUser(Post post, User user);
}
