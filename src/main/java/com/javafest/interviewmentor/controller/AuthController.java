package com.javafest.interviewmentor.controller;

import com.javafest.interviewmentor.dto.RegisterRequest;
import com.javafest.interviewmentor.dto.LoginRequest;
import com.javafest.interviewmentor.dto.JwtResponse;
import com.javafest.interviewmentor.entity.User;
import com.javafest.interviewmentor.entity.Role;
import com.javafest.interviewmentor.repository.UserRepository;
import com.javafest.interviewmentor.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            return ResponseEntity.badRequest().body("Username already exists");
        if (userRepository.findByEmail(request.getEmail()).isPresent())
            return ResponseEntity.badRequest().body("Email already exists");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findByName("ROLE_LEARNER")
            .orElseThrow(() -> new RuntimeException("Default role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generate JWT
        try {
            JWSSigner signer = new MACSigner(jwtSecret.getBytes());
            Date now = new Date();
            Date expiry = new Date(now.getTime() + 3600_000); // 1 hour
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(request.getUsername())
                .issueTime(now)
                .expirationTime(expiry)
                .claim("roles", authentication.getAuthorities().stream().map(a -> a.getAuthority()).toArray())
                .build();
            SignedJWT signedJWT = new SignedJWT(
                new com.nimbusds.jose.JWSHeader(JWSAlgorithm.HS256),
                claimsSet
            );
            signedJWT.sign(signer);
            String token = signedJWT.serialize();
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to generate JWT");
        }
    }
} 