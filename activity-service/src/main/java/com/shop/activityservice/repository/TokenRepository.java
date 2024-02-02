package com.shop.activityservice.repository;


import com.shop.activityservice.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByToken(String token);
    List<Token> findAllByEmail(String email);
}
