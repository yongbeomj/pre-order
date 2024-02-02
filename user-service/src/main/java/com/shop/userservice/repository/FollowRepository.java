package com.shop.userservice.repository;

import com.shop.userservice.domain.Follow;
import com.shop.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserAndToUser(User fromUser, User toUser);
    List<Follow> findAllByFromUserId(Long fromUserId);

}
