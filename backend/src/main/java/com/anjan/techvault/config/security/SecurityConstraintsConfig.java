package com.anjan.techvault.config.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.auth.LoginConfig;

import io.quarkus.security.Authenticated;

/**
 * Security constraints configuration for Quarkus.
 *
 * This class configures the security constraints for the application.
 * It uses the JWT authentication mechanism.
 *
 * Note: Individual endpoints can be secured using the @Authenticated, @RolesAllowed,
 * @PermitAll, and @DenyAll annotations.
 */
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT")
public class SecurityConstraintsConfig extends Application {
    // The configuration is done through annotations and application.properties
}