package de.nikos410.ina.webshop.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Simple PasswordEncoder implementation that uses the SHA-256 algorithm.
 * <p>
 * Since SHA-256 on it's own is a pretty weak algorithm and this encoder does not use any salt, <strong>this
 * implementation should not be used for sensitive applications.</strong>
 */
public class Sha256PasswordEncoder implements PasswordEncoder {

    private final MessageDigest digest;

    public Sha256PasswordEncoder() {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String encode(String rawPassword) {
        final byte[] encodedBytes = digest.digest(rawPassword.getBytes(UTF_8));
        final String encodedHex = bytesToHex(encodedBytes);
        return "sha-256 " + encodedHex;
    }

    private String bytesToHex(byte[] bytes) {
        final StringBuilder hexBuilder = new StringBuilder();
        for (byte b : bytes) {
            final String hexValue = String.format("%02x", b);
            hexBuilder.append(hexValue);
        }

        return hexBuilder.toString();
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encode(rawPassword).equals(encodedPassword);
    }
}
