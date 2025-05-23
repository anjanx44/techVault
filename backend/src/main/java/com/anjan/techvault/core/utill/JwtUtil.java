package com.anjan.techvault.core.utill;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.jwt.JsonWebToken;

import jakarta.inject.Inject;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtUtil {

    @Inject
    JsonWebToken jwt;

    private final String ISSUER = "https://techvault.com/issuer";

    public String extractUsername(String token) {
        // In Quarkus, token validation and extraction is handled by the runtime
        // This is a simplified version for compatibility
        return jwt.getName();
    }

    public long extractExpiration() {
        return jwt.getExpirationTime();
    }

    public String generateToken(String username) {
        Set<String> groups = new HashSet<>();
        groups.add("user");

        return Jwt.issuer(ISSUER)
                .subject(username)
                .groups(groups)
                .expiresIn(Duration.ofHours(10)) // 10 hours validity
                .sign();
    }

    public Boolean validateToken(String token, String username) {
        // In Quarkus, token validation is handled by the runtime
        // This is a simplified version for compatibility
        return jwt.getName().equals(username) && !isTokenExpired();
    }

    private Boolean isTokenExpired() {
        return Instant.ofEpochSecond(extractExpiration()).isBefore(Instant.now());
    }
}