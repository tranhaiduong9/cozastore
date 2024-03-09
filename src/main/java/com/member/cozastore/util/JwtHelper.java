package com.member.cozastore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {

    @Value("${custom.token.key}")
    private String secKey;

    private long expiredTime = 8 * 60 * 60 * 1000;
    private long SignupExpiredTimeToken =2 * 60 * 60 * 1000;

    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secKey));

        Date date = new Date();
        long newDateMilis = date.getTime() + expiredTime;
        Date newExpiredDate = new Date(newDateMilis);

        String token = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .setExpiration(newExpiredDate)
                .compact();
        return token;
    }
    public String parserToken (String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode((secKey)));

        String data = Jwts.parser()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
        return data;
    }

    public String generateSignupToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secKey));

        Date date = new Date();
        long newDateMilis = date.getTime() + SignupExpiredTimeToken;
        Date newExpiredDate = new Date(newDateMilis);

        String token = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .setExpiration(newExpiredDate)
                .compact();
        return token;
    }

    public Date getExprirationToken (String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode((secKey)));

        Date data = Jwts.parser()
                .setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getExpiration();
        return data;
    }
}
