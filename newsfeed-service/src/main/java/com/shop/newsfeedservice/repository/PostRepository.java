package com.shop.newsfeedservice.repository;


import com.shop.newsfeedservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
