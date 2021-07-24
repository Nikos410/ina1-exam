package de.nikos410.ina.webshop.model;

public record RegisterRequest(String username, String password, String fullName, String emailAddress) {
}
