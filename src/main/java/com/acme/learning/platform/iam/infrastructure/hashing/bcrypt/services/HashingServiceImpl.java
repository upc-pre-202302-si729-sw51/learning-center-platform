package com.acme.learning.platform.iam.infrastructure.hashing.bcrypt.services;

import com.acme.learning.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Hashing service implementation.
 * <p>
 *     This class is used to hash a password.
 *     It uses the BCrypt algorithm.
 * </p>
 */
@Service
public class HashingServiceImpl implements HashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * @InheritDoc
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
