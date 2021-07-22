package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.exception.RequestMethodNotSupportedException;
import de.nikos410.ina.webshop.util.AuthenticationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;

public abstract class ControllerHelper {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    protected ControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void doGet() throws ServletException, IOException {
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

    protected void requireSessionAuthenticated() throws IOException {
        if (!AuthenticationUtils.isSessionAuthenticated(getRequest())) {
            redirect("/login");
        }
    }

    protected void redirect(String path) throws IOException {
        final String redirectUrl = buildRedirectUrl(path);
        getResponse().sendRedirect(redirectUrl);
    }

    protected void forward(String path) throws ServletException, IOException {
        final var requestDispatcher = getRequest().getRequestDispatcher(path);
        requestDispatcher.forward(getRequest(), getResponse());
    }

    private String buildRedirectUrl(String destination) {
        final String contextPath = getRequest().getContextPath();
        return getResponse().encodeRedirectURL(contextPath + destination);
    }

    protected <T> T getSessionAttribute(String attributeName, Class<T> attributeType) {
        final Object value = getRequest().getSession().getAttribute(attributeName);
        if (isNull(value)) {
            return null;
        }

        if (attributeType.isAssignableFrom(value.getClass())) {
            return (T) value;
        } else {
            throw new IllegalStateException(
                    format("Unexpected attribute type for attribute {0}. Expected type {1} but encountered {2}.",
                            attributeName, attributeName, value.getClass()));
        }
    }

    protected void setSessionAttribute(String attributeName, Object attributeValue) {
        getRequest().getSession().setAttribute(attributeName, attributeValue);
    }
}
