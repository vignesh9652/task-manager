package com.project.task_manager.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private static final String SECRET_KEY = "mySuperSecretKeyForTaskManagerApllicationJwtAuthentication2026";

    public String generateToken(String email){
       return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token){
       return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token ,String email){
         String extractedEmail = extractEmail(token);
         boolean isEmailMatched = extractedEmail.equals(email);
         boolean isExpired = isTokenExpired(token);
        return isEmailMatched && !isExpired ;
    }


    public boolean isTokenExpired(String token){
        Date expirationDate = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
       return expirationDate.before(new Date());
    }
}
