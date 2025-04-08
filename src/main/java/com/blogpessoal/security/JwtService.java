package com.blogpessoal.security;

import java.security.Key;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final long EXPIRACAO = 86400000L; //
    private static final String CHAVE_SECRETA = "q5v8k3yF1hB7jRtZP2mN6sX9wL0oC4eD/KlOiUfVnAx=";

    private Key gerarChave() {
        return Keys.hmacShaKeyFor(CHAVE_SECRETA.getBytes());
    }

    public String gerarToken(String userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.intern())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACAO))
                .signWith(gerarChave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(gerarChave())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(gerarChave())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
