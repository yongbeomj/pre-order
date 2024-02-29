package com.shop.userservice.user.service;

import com.shop.userservice.user.entity.CustomUserDetails;
import com.shop.userservice.user.entity.User;
import com.shop.userservice.common.exception.BaseException;
import com.shop.userservice.common.response.ErrorCode;
import com.shop.userservice.user.repository.UserRepository;
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
