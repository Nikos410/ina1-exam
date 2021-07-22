package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.util.AuthenticationUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
        getRequest().getSession().setAttribute(AuthenticationUtils.AUTHENTICATED_USER_ATTRIBUTE_NAME, null);
    }
}
