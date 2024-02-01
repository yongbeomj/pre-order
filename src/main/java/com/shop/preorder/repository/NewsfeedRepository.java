package com.shop.preorder.repository;

import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.Newsfeed;
import com.shop.preorder.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findAllByActivityUserId(Long activityUserId);
}
