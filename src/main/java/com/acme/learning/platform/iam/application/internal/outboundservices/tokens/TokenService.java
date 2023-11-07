package com.acme.learning.platform.iam.application.internal.outboundservices.tokens;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);

    String getUsernameFromToken(String token);

    boolean validateToken(String token);
}

