package com.hrm.system.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 三多
 * @Time 2020/3/24
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        //生成jwt
        String token = createJwt();
        System.out.println(token);
        //解析jwt
        String result = parseJwt(token);
        System.out.println(result);
    }

    /**
     * 解析
     *
     * @param token
     * @return
     */
    private static String parseJwt(String token) {
        Jws<Claims> parseClaimsJws = Jwts.parser()
                .setSigningKey("sanduo")
                .parseClaimsJws(token);
        Claims jwsBody = parseClaimsJws.getBody();
        String subject = jwsBody.getSubject();
        String id = jwsBody.getId();
        String username = String.valueOf(jwsBody.get("username"));
        String password = String.valueOf(jwsBody.get("password"));
        Date issuedAt = jwsBody.getIssuedAt();
        String issueTime = formatTime(issuedAt);
        StringBuilder builder = new StringBuilder();
        return builder
                .append(id).append(":")
                .append(subject).append(":")
                .append(subject).append(":")
                .append(issueTime).append(":")
                .append(username).append(":")
                .append(password).append(":").toString();
    }

    private static String formatTime(Date issuedAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Instant instant = issuedAt.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, zoneId);
        return formatter.format(dateTime);
    }

    /**
     * 创建
     *
     * @return
     */
    private static String createJwt() {
        Map claims = new HashMap();
        claims.put("username", "admin");
        claims.put("password", "123");
        return Jwts.builder().setId("888")
                .setSubject("sanduo")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "sanduo")
                .addClaims(claims)
                .compact();
    }
}
