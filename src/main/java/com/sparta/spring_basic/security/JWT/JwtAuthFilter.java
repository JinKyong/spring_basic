package com.sparta.spring_basic.security.JWT;

import com.sparta.spring_basic.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    // request header 에서 토큰을 가져오거나

    // Request로 들어오는 Jwt Token의 유효성을 검증하는 filter를 filterChain에 등록합니다.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("access-token");
        String refreshToken = request.getHeader("refresh-token");

        if (accessToken != null && jwtProvider.validateToken(accessToken)) {   // token 검증
            Authentication auth = jwtProvider.getAuthentication(accessToken);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
        }
        else if(refreshToken!= null && jwtProvider.validateToken(refreshToken)){
            accessToken = jwtProvider.generateAccessToken(refreshToken); //token 재발금
            Authentication auth = jwtProvider.getAuthentication(accessToken);    // 인증 객체 생성
            SecurityContextHolder.getContext().setAuthentication(auth); // SecurityContextHolder에 인증 객체 저장
            response.addHeader("access-Token",accessToken);
        }
        filterChain.doFilter(request, response);
    }
}
