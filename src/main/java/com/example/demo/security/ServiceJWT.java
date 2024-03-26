package com.example.demo.security;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.nimbusds.jwt.JWTClaimsSet;

@Service
public class ServiceJWT {
    public static String extractUsername(String jwtToken) {

    try {
        // Séparer les trois parties du jeton JWT
        String[] parts = jwtToken.split("\\.");
        
        // Vérifier que le jeton a trois parties
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid JWT token format");
        }
        
        // Extraire la charge utile (payload) et la décoder
        String payload = new String(Base64.getDecoder().decode(parts[1]));
        
        // Parse the JWT claims set from the payload
        JWTClaimsSet claimsSet = JWTClaimsSet.parse(payload);
        
        // Extract the username from the claims
        String username = claimsSet.getSubject();
        
        return username;
    } catch (Exception e) {
        // Handle parsing exceptions
        e.printStackTrace();
        return null;
    }
}}