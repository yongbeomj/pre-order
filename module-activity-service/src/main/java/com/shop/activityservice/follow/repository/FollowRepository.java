package com.shop.activityservice.follow.repository;

import com.shop.activityservice.follow.entiity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    List<Follow> findAllByFromUserId(Long fromUserId);

}
