package com.shop.activityservice.repository;

import com.shop.activityservice.domain.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

}
