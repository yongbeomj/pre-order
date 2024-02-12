package com.shop.newsfeedservice.repository;


import com.shop.newsfeedservice.domain.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findAllByActivityUserId(Long activityUserId);
}
