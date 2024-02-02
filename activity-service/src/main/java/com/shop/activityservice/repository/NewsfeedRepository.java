package com.shop.activityservice.repository;

import com.shop.activityservice.domain.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findAllByActivityUserId(Long activityUserId);
}
