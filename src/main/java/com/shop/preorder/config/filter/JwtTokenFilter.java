package com.shop.preorder.config.filter;

import com.shop.preorder.domain.CustomUserDetails;
import com.shop.preorder.service.CustomUserDetailsService;
import com.shop.preorder.service.UserService;
import com.shop.preorder.util.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

//    private final UserService userService;
    private final CustomUserDetailsService userDetailsService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ")) {
            log.error("헤더 에러 발생");
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = header.split(" ")[1].trim();
            // TODO : token validation
            if (JwtTokenUtil.isTokenExpired(token, secretKey)) {
                log.error("토큰 만료");
                filterChain.doFilter(request, response);
                return;
            }

            // TODO : get username from token
            String userName = JwtTokenUtil.getUserName(token, secretKey);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

            // TODO : username validation

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (RuntimeException e) {
            log.error("validation error, {}", e.toString());
            filterChain.doFilter(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
