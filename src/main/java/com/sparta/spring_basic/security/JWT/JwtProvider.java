package com.sparta.spring_basic.security.JWT;

import com.sparta.spring_basic.dto.TokenDto;
import com.sparta.spring_basic.entity.RefreshToken;
import com.sparta.spring_basic.entity.User;
import com.sparta.spring_basic.repository.RefreshTokenRepository;
import com.sparta.spring_basic.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtProvider {
    //매크로
    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;



    //private static final String BEARER_TYPE = "bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * MINUTE;    // 60분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 7 * DAY;       // 7일



    private static final String SECRET_KEY = "ajhrlagh43982jkldgfsggghsfhhsdhdshdsgfddnbskjls9024";
    private final byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    private final Key key = Keys.hmacShaKeyFor(keyBytes);



    //repository & service
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    //토큰 생성
    public TokenDto generateTokenDto(User user) {
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .setSubject(user.getNickname())                             //토큰 이름
                .setIssuedAt(new Date())                                    //생성일
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))    //만료일
                .signWith(key, SignatureAlgorithm.HS256)                    //서명
                .compact();

        String refreshToken = Jwts.builder()
                .setSubject(user.getNickname())
                .setIssuedAt(new Date())                                    //생성일
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))    //만료일
                .signWith(key, SignatureAlgorithm.HS256)                    //서명
                .compact();

        RefreshToken refreshTokenObject = new RefreshToken(user.getNickname(),refreshToken);
        refreshTokenRepository.save(refreshTokenObject);

        return new TokenDto(accessToken, refreshToken);
    }

    public String generateAccessToken(String token){
        long now = new Date().getTime();
        String username = getUserNameFromToken(token);

        return Jwts.builder()
                .setSubject(username)                             //토큰 이름
                .setIssuedAt(new Date())                                    //생성일
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))    //만료일
                .signWith(key, SignatureAlgorithm.HS256)                    //서명
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String username = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody().getSubject();
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // Jwt 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("잘못된 JWT 토큰입니다.");
        } catch (ExpiredJwtException ex) {
            log.error("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException ex) {
            log.error("지원하지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException ex) {
            log.error("JWT 토큰이 비었습니다.");
        }
        return false;
    }

    public String getUserNameFromToken(String token){
        return getClaimsFromToken(token).getSubject();
    }

    public Date getExpiredDateFromToken(String token){
        return getClaimsFromToken(token).getExpiration();
    }

    public Claims getClaimsFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJwt(token).getBody();
    }
}
