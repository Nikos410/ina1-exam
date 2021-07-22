package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.exception.BadCredentialsException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static de.nikos410.ina.webshop.util.AuthenticationUtils.authenticate;


public class LoginControllerHelper extends ControllerHelper {

    public LoginControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        forward("/login-form");
    }

    @Override
    public void doPost() {

        try {
            authenticate(getRequest());
            // TODO: redirect to shop page
        } catch (BadCredentialsException e) {
            redirect("/login?error");
        }
    }
}
