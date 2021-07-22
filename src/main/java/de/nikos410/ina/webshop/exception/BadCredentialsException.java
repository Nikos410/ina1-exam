package de.nikos410.ina.webshop.exception;

public class BadCredentialsException extends AuthenticationException {

    public BadCredentialsException(String message) {
        super(message);
    }
}
