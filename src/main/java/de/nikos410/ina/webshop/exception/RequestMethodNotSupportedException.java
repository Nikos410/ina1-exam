package de.nikos410.ina.webshop.exception;

public class RequestMethodNotSupportedException extends RuntimeException {

    public RequestMethodNotSupportedException(String message) {
        super(message);
    }
}
