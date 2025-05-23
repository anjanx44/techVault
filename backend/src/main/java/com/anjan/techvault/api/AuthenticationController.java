package com.anjan.techvault.api;

import com.anjan.techvault.core.utill.JwtUtil;
import com.anjan.techvault.domain.user.User;
import com.anjan.techvault.dto.AuthenticationResponse;
import com.anjan.techvault.dto.RegistrationRequest;
import com.anjan.techvault.service.user.UserService;
import com.anjan.techvault.config.security.SecurityConfig.PasswordEncoder;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.WebApplicationException;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticationController {

    private final UserService userService; // Service for user-related operations
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Inject
    public AuthenticationController(UserService userService, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @POST
    @Path("/register")
    @PermitAll
    public Response registerUser(RegistrationRequest request) {
        // Check if username/email is already taken
        if (userService.existsByUsername(request.getUsername())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Username already exists")
                    .build();
        }

        // Create a new user entity from the registration request
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        // Save the user to the database
        userService.save(user);

        return Response.ok("User registered successfully").build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(RegistrationRequest request) {
        try {
            // Validate user credentials
            User user = userService.findByUsername(request.getUsername())
                    .orElseThrow(() -> new WebApplicationException("Invalid credentials", Response.Status.UNAUTHORIZED));

            // If password is correct, generate JWT
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                return Response.ok(new AuthenticationResponse(token)).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid credentials")
                        .build();
            }
        } catch (WebApplicationException e) {
            throw e;
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred during authentication")
                    .build();
        }
    }
}