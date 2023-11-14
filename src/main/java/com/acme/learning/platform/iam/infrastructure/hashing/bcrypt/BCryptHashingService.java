package com.acme.learning.platform.iam.infrastructure.hashing.bcrypt;

import com.acme.learning.platform.iam.application.internal.outboundservices.hashing.HashingService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface BCryptHashingService extends HashingService, PasswordEncoder {

}
