package com.shop.userservice.service;

import com.shop.userservice.domain.CustomUserDetails;
import com.shop.userservice.domain.User;
import com.shop.userservice.exception.BaseException;
import com.shop.userservice.exception.ErrorCode;
import com.shop.userservice.repository.UserRepository;
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
