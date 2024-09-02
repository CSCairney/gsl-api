package com.gsl.glasgowsocialleague.web.security;

import com.gsl.glasgowsocialleague.core.model.account.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds; // e.g., 3600000 (1 hour)

    public String generateToken(Account account) {
        Claims claims = Jwts.claims().setSubject(account.getEmail());
        claims.put("role", account.getRole().getName());
        log.info("Generated JWT token: {}", claims.toString());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)  // Use the plain secret key here
                .compact();
    }

    public boolean validateToken(String token) {
        log.info("Validating JWT token: {}", token);
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)  // Use the same plain secret key here
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT validation error: ", e);
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)  // Use the same plain secret key here
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)  // Use the same plain secret key here
                .parseClaimsJws(token)
                .getBody();
        String role = (String) claims.get("role");

        return List.of(new SimpleGrantedAuthority(role));
    }
}

