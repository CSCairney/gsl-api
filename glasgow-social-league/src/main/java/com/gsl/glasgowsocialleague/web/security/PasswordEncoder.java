package com.gsl.glasgowsocialleague.web.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
@Service
public class PasswordEncoder {

    private static final SecureRandom random = new SecureRandom();

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // Log the salt for debugging
        log.debug("Generated salt: {}", Base64.getEncoder().encodeToString(salt));

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes());

        // Log the hashed password for debugging
        log.debug("Generated hashed password: {}", Base64.getEncoder().encodeToString(hashedPassword));

        return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);
    }

    public static boolean verifyPassword(String password, String storedHash) throws NoSuchAlgorithmException {
        String[] parts = storedHash.split(":");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedPasswordHash = Base64.getDecoder().decode(parts[1]);

        // Log the salt and stored password hash for debugging
        log.debug("Stored salt: {}", Base64.getEncoder().encodeToString(salt));
        log.debug("Stored hashed password: {}", Base64.getEncoder().encodeToString(storedPasswordHash));

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);

        byte[] hashedPassword = md.digest(password.getBytes());

        // Log the hashed password for comparison
        log.debug("Hashed password for comparison: {}", Base64.getEncoder().encodeToString(hashedPassword));

        return MessageDigest.isEqual(storedPasswordHash, hashedPassword);
    }
}
