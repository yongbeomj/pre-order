package com.shop.preorder.repository;

import com.shop.preorder.domain.Follow;
import com.shop.preorder.domain.User;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFromUserAndToUser(User fromUser, User toUser);
    List<Follow> findAllByFromUserId(Long fromUserId);

}
