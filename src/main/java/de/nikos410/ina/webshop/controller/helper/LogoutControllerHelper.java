package de.nikos410.ina.webshop.controller.helper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static de.nikos410.ina.webshop.controller.helper.ShoppingCartControllerHelper.SHOPPING_CART_ATTRIBUTE_NAME;
import static de.nikos410.ina.webshop.util.AuthenticationUtils.AUTHENTICATED_USER_ATTRIBUTE_NAME;

public class LogoutControllerHelper extends ControllerHelper {

    public LogoutControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws IOException {
        clearSession();
        redirect("/login");
    }

    private void clearSession() {
        setSessionAttribute(AUTHENTICATED_USER_ATTRIBUTE_NAME, null);
        setSessionAttribute(SHOPPING_CART_ATTRIBUTE_NAME, null);
    }
}
