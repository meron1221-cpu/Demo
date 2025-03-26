package com.crud_operation.usermanagment.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTUtils {
    private SecretKey key;
    public static final long EXPIRATION_TIME = 86400000; // 24 HOURS

    public JWTUtils() {
        try {
            // Use a valid Base64-encoded secret string
            String secretString = "84356789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ12";
            byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
            this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize JWTUtils: " + e.getMessage(), e);
        }
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // Set the subject (username)
                .setIssuedAt(new Date()) // Set the issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with the key
                .compact(); // Compact it into a string
    }

    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims) // Add custom claims
                .setSubject(userDetails.getUsername()) // Set the subject (username)
                .setIssuedAt(new Date()) // Set the issued date
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with the key
                .compact(); // Compact it into a string
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject); // Extract the subject (username) from the token
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token); // Extract the username from the token
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)); // Check if the token is valid
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        final Claims claims = extractAllClaims(token); // Extract all claims from the token
        return claimsTFunction.apply(claims); // Apply the resolver function to extract specific claims
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key) // Set the signing key
                .build()
                .parseClaimsJws(token) // Parse the token
                .getBody(); // Get the claims
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date()); // Check if the token is expired
    }
}