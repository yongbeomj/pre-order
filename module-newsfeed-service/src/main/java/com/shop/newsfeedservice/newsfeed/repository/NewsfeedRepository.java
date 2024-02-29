package com.shop.newsfeedservice.newsfeed.repository;


import com.shop.newsfeedservice.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findAllByActivityUserId(Long activityUserId);
}
