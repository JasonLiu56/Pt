package com.jxcia.pt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@ConfigurationProperties(prefix = "jxcia.jwt")
public class JwtUtil {

    private Long expire;
    private String secret;
    private String header;

    public String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 1000 * expire);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)  // 7天过期
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimsByToken(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    // 判断JWT是否过期
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}