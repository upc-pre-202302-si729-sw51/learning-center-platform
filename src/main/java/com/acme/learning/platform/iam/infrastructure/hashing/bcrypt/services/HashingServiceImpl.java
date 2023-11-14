package com.acme.learning.platform.iam.infrastructure.hashing.bcrypt.services;

import com.acme.learning.platform.iam.infrastructure.hashing.bcrypt.BCryptHashingService;
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
public class HashingServiceImpl implements BCryptHashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Hashes a password.
     * @param rawPassword the password to hash.
     * It must be a {@link CharSequence} object.
     * return the hashed password.
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Matches a raw password with an encoded password.
     * @param rawPassword the raw password to match.
     * It must be a {@link CharSequence} object.
     * @param encodedPassword the encoded password to match.
     * @return true if the raw password matches the encoded password, false otherwise.
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
