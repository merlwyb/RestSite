/*
 * Copyright (c) 2008-2020
 * LANIT
 * All rights reserved.
 *
 * This product and related documentation are protected by copyright and
 * distributed under licenses restricting its use, copying, distribution, and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * LANIT and its licensors, if any.
 *
 * $
 */
package com.example.rest.security.jwt;

import com.example.rest.security.UserPrinciple;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import static java.time.temporal.ChronoUnit.SECONDS;

@Component //JwtUtils
public class JwtProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);
    private static final String JWT_SIGN_SECRET = RandomStringUtils.random(32, true, true);

    @Value("${rest.security.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(UserPrinciple userPrincipal) {
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(jwtExpiration, SECONDS)))
                .claim("currentUser", toJsonString(userPrincipal))
                .signWith(SignatureAlgorithm.HS512, JWT_SIGN_SECRET)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SIGN_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SIGN_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.debug("Invalid JWT signature -> Message: {} ", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.debug("Invalid JWT token -> Message: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.debug("Expired JWT token -> Message: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.debug("Unsupported JWT token -> Message: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.debug("JWT claims string is empty -> Message: {}", e.getMessage());
        }

        return false;
    }

    private String toJsonString(Serializable object) {
        ObjectWriter writer = new ObjectMapper().writer();
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(String.format("Could not transform object '%s' to JSON: ", object), e);
        }
    }
}
