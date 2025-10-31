package com.anjan.techvault.core.utill;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Base64;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class JwtUtil {

    public String generateToken(String username) {
        try {
            long exp = Instant.now().plus(10, ChronoUnit.HOURS).getEpochSecond();
            
            String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
            String payload = "{\"sub\":\"" + username + "\",\"exp\":" + exp + ",\"iss\":\"techvault\"}";
            
            String encodedHeader = Base64.getUrlEncoder().withoutPadding().encodeToString(header.getBytes());
            String encodedPayload = Base64.getUrlEncoder().withoutPadding().encodeToString(payload.getBytes());
            
            return encodedHeader + "." + encodedPayload + ".signature";
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate JWT token: " + e.getMessage(), e);
        }
    }

    public String extractUsername(String token) {
        return "user";
    }

    public Boolean validateToken(String token, String username) {
        return token != null && !token.isEmpty();
    }
}