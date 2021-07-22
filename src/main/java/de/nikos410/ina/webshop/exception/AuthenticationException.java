package de.nikos410.ina.webshop.exception;

public abstract class AuthenticationException extends Exception {

    public AuthenticationException(String message) {
        super(message);
    }
}
