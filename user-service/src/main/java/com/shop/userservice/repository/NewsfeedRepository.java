package com.shop.userservice.repository;

import com.shop.userservice.domain.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    List<Newsfeed> findAllByActivityUserId(Long activityUserId);
}
