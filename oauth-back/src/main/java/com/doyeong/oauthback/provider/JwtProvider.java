package com.doyeong.oauthback.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// ioc(제어역전) 추가
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
  
  private final String SECREAT_KEY = "P!ssw0rd";

  // 생성 메서드
  public String create(String id) {

    Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

    String jwt = Jwts.builder()
      .signWith(SignatureAlgorithm.HS256, SECREAT_KEY)
      .setSubject(id).setIssuedAt(new Date()).setExpiration(expiredDate)
      // 생성
      .compact();

    return jwt;

  }

  // 검증
  public String validate(String jwt) {

    Claims payload = null;

    try {
      payload = Jwts.parser()
        .setSigningKey(SECREAT_KEY)
        .parseClaimsJws(jwt).getBody();
    } catch (Exception exception) {
      exception.printStackTrace();
      return null;
    }

    return payload.getSubject();
    
  }

}
