package com.example.demofilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {

    // Define a fixed secret key (ensure this is stored securely in real applications)
    private static final String SECRET_KEY = "my-fixed-secret-key-which-should-be-very-secure-and-long-enough";

    private static Key getKey() {
        byte[] decodedKey = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
    }

    public String createJwt(UserProfile userProfile, long minuteTimeOut) {
        Key key = getKey();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .claim("I_USER", userProfile.getId())
                .claim("F_NAME", userProfile.getFirstName())
                .claim("L_NAME", userProfile.getLastName())
                .claim("U_EMAIL", userProfile.getEmail())
                .claim("U_ROLES", userProfile.getRoles())
                .claim("TIMESTAMP", new Date())
                .setIssuer("ABC")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(minuteTimeOut).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(key)
                .compact();
    }

    public Claims getClaims(@NonNull String token) {
        Key key = getKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
