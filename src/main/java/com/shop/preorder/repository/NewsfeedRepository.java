package com.shop.preorder.repository;

import com.shop.preorder.domain.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
}
