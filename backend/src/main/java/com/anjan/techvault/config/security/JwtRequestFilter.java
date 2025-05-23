package com.anjan.techvault.config.security;

import com.anjan.techvault.core.utill.JwtUtil;
import com.anjan.techvault.service.user.CustomUserDetailsService;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.io.IOException;
import java.security.Principal;

/**
 * JWT request filter for Quarkus.
 *
 * Note: This filter is not strictly necessary in Quarkus as JWT validation
 * is handled automatically by the MicroProfile JWT implementation.
 * This class is kept for compatibility and additional custom processing if needed.
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class JwtRequestFilter implements ContainerRequestFilter {

    @Inject
    JwtUtil jwtUtil;

    @Inject
    CustomUserDetailsService userDetailsService;

    @Inject
    JsonWebToken jwt;

    @Inject
    SecurityIdentity securityIdentity;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        // Most of the JWT validation is handled automatically by Quarkus/MicroProfile JWT
        // This filter can be used for additional custom processing if needed

        // Example: Log the current authenticated user if available
        if (jwt != null && jwt.getName() != null) {
            // User is authenticated, jwt.getName() contains the username
            // Additional custom processing can be done here if needed
        }

        // No need to manually set authentication as Quarkus handles this automatically
    }
}