package de.nikos410.ina.webshop.security;

/**
 * Specifies operations for encoding and matching encoded passwords.
 */
public interface PasswordEncoder {

    /**
     * Encodes the raw password.
     *
     * @param rawPassword The raw password.
     * @return The encoded password.
     */
    String encode(String rawPassword);

    /**
     * Verify the encoded password matches the raw password after it too is encoded.
     * The stored password itself is never decoded.
     *
     * @param rawPassword The raw password.
     * @param encodedPassword The encoded password.
     * @return True if the raw password, after encoding, matches the encoded password.
     * @implSpec The encoded password should never be decoded.
     */
    boolean matches(String rawPassword, String encodedPassword);
}
