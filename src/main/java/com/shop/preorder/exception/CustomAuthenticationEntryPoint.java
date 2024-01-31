package com.shop.preorder.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.preorder.dto.common.ErrorResponse;
import com.shop.preorder.dto.common.ResponseDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(ErrorCode.INVALID_TOKEN.getHttpStatus().value());

        ObjectMapper objectMapper = new ObjectMapper();
        String errorObjectString = objectMapper.writeValueAsString(ResponseDto.error(ErrorResponse.of(ErrorCode.INVALID_TOKEN)));
        response.getWriter().write(errorObjectString);
    }

}
