package com.example.jwt.util;

import com.example.jwt.model.JwtInfo;
import com.example.jwt.model.UserInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    public static final String CLAIM_KEY_AUTHORITIES = "authorities";
    @Value("${jwt.secret:#{null}}")
    private String jwtSecret;
    @Value("${jwt.expired:#{null}}")
    private Integer jwtExpired;
    @Value("${jwt.tokenType:#{null}}")
    private String jwtTokenType;

    private final ObjectMapper objectMapper;

    public JwtInfo generateAccessToken(UserInfo userInfo) {
        Date issuedAt = new Date();
        Date expiration = new Date(issuedAt.getTime() + (jwtExpired));
        String token = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userInfo.getUsername())
                .claim(CLAIM_KEY_AUTHORITIES, userInfo)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        return new JwtInfo(token, userInfo.getUsername(), issuedAt, expiration, jwtExpired);
    }

    public String getAccessToken(HttpServletRequest request) {
        String authenticationString = request.getHeader("Authorization");

        return this.getAccessToken(authenticationString);
    }

    public String getAccessToken(String authorizationString) {
        if (authorizationString != null && authorizationString.startsWith(jwtTokenType)) {
            return authorizationString.replaceFirst(jwtTokenType + " ", org.apache.commons.lang3.StringUtils.EMPTY);
        }

        return null;
    }

    public Claims extractToken(String token) {
        return Jwts.
                parserBuilder().
                setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserInfo userInfo) {
        final String username = extractUsername(token);
        return (username.equals(userInfo.getUsername())) && !isTokenExpired(token);
    }

    @SuppressWarnings("rawtypes")
    public UserInfo getUserInfo(Claims claims) {
        Map map = (Map) claims.get(JwtUtil.CLAIM_KEY_AUTHORITIES);

        return objectMapper.convertValue(map, UserInfo.class);
    }

    private String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractToken(token);
        return claimsResolver.apply(claims);
    }

    private Key getSignKey() {
        byte[] keys = jwtSecret.getBytes();

        return Keys.hmacShaKeyFor(keys);
    }


}