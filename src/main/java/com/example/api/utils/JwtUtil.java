package com.example.api.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

public class JwtUtil {
    public final static String TOKEN_NAME = "Authorization";

    public final static long REMEMBER_EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public final static long EXPIRATION_TIME = 1000 * 60 * 60;

    private static final String SECRET_KEY = "logistics.api";

    private static final String TOKEN_HEADER = "logisticsSystem:";

    public static boolean checkToken(String token) {
        if (token == null) return false;
        return token.startsWith(TOKEN_HEADER);
    }

    public static String createToken(String email, String[] roles, boolean isRemember) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("email", email);
        long expiration = isRemember ? REMEMBER_EXPIRATION_TIME : EXPIRATION_TIME;
        Date now = new Date();

        return TOKEN_HEADER + Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public static Claims getTokenClaims(String token) {
        String realToken = token.substring(TOKEN_HEADER.length());
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(realToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public static String getTokenEmail(String token) {
        return (String) getTokenClaims(token).get("email");
    }

    public static List<String> getTokenRoles(String token) {
        List<String> roles = new ArrayList<>();
        Claims claims = getTokenClaims(token);
        Object rolesObject = claims.get("roles");
        for (Object o : (List<?>) rolesObject) {
            roles.add((String) o);
        }
        return roles;
    }

    public static boolean isExpiration(String token) {
        return getTokenClaims(token).getExpiration().before(new Date());
    }
}
