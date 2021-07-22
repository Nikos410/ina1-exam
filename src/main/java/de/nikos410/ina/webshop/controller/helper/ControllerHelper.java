package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.exception.RequestMethodNotSupportedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class ControllerHelper {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    protected ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void doGet() throws ServletException, IOException{
        throw new RequestMethodNotSupportedException("Request method GET not supported for this operation.");
    }

    public void doPost() throws ServletException, IOException {
        throw new RequestMethodNotSupportedException("Request method POST not supported for this operation.");
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }

    protected void redirect(String path) {
        final String redirectUrl = buildRedirectUrl(path);
        try {
            getResponse().sendRedirect(redirectUrl);
        } catch (IOException e) {
            throw new IllegalStateException("Could not redirect to " + path, e);
        }
    }

    protected void forward(String path) throws ServletException, IOException {
        final var requestDispatcher = getRequest().getRequestDispatcher(path);
        requestDispatcher.forward(getRequest(), getResponse());
    }

    private String buildRedirectUrl(String destination) {
        final String contextPath = getRequest().getContextPath();
        return getResponse().encodeRedirectURL(contextPath + destination);
    }
}
