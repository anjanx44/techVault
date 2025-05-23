package com.anjan.techvault.config.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.IteratedSaltedPasswordAlgorithmSpec;
import org.wildfly.security.password.util.ModularCrypt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Security configuration for Quarkus.
 *
 * Note: Most of the security configuration is done through application.properties
 * and annotations on the resource classes.
 */
@ApplicationScoped
public class SecurityConfig {

    private final SecureRandom random = new SecureRandom();

    /**
     * Produces a password encoder that uses BCrypt.
     */
    @Produces
    @Singleton
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder();
    }

    /**
     * Simple password encoder that uses BCrypt.
     */
    public static class PasswordEncoder {
        private final WildFlyElytronPasswordProvider provider = new WildFlyElytronPasswordProvider();

        /**
         * Encode a password using BCrypt.
         */
        public String encode(String rawPassword) {
            try {
                PasswordFactory factory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);

                byte[] salt = new byte[16];
                new SecureRandom().nextBytes(salt);

                IteratedSaltedPasswordAlgorithmSpec spec = new IteratedSaltedPasswordAlgorithmSpec(10, salt);
                EncryptablePasswordSpec eps = new EncryptablePasswordSpec(rawPassword.toCharArray(), spec);

                BCryptPassword bcryptPassword = (BCryptPassword) factory.generatePassword(eps);
                return ModularCrypt.encodeAsString(bcryptPassword);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException("Error encoding password", e);
            }
        }

        /**
         * Check if a raw password matches an encoded password.
         */
        public boolean matches(String rawPassword, String encodedPassword) {
            try {
                PasswordFactory factory = PasswordFactory.getInstance(BCryptPassword.ALGORITHM_BCRYPT, provider);
                BCryptPassword bcryptPassword = (BCryptPassword) factory.translate(ModularCrypt.decode(encodedPassword));
                return factory.verify(bcryptPassword, rawPassword.toCharArray());
            } catch (Exception e) {
                return false;
            }
        }
    }
}