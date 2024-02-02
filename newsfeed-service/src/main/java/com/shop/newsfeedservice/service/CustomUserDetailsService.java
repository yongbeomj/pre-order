package com.shop.newsfeedservice.service;

import com.shop.newsfeedservice.domain.CustomUserDetails;
import com.shop.newsfeedservice.domain.User;
import com.shop.newsfeedservice.exception.BaseException;
import com.shop.newsfeedservice.exception.ErrorCode;
import com.shop.newsfeedservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        log.debug("loadUserByUsername user : {}", user);

        return new CustomUserDetails(user);
    }
}
