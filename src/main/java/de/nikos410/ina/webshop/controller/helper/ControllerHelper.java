package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.exception.RequestMethodNotSupportedException;
import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.util.AuthenticationUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;

public abstract class ControllerHelper {

    public static final String AUTHENTICATED_USER_ATTRIBUTE_NAME = "authenticatedUser";

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
        if (!isSessionAuthenticated()) {
            redirect("/login");
        }
    }

    private boolean isSessionAuthenticated() {
        return AuthenticationUtils.getAuthenticatedUser(getRequest())
                .isPresent();
    }

    protected void requireSessionAuthenticated(String username) throws IOException {
        if (!isSessionAuthenticated(username)) {
            redirect("/login?error=You%20are%20not%20authorized%20to%20access%20this%20page.");
        }
    }

    private boolean isSessionAuthenticated(String username) {
        final Optional<User> authenticatedUser =  AuthenticationUtils.getAuthenticatedUser(getRequest());
        return authenticatedUser.isPresent() && username.equals(authenticatedUser.get().getUsername());
    }

    protected void redirect(String path) throws IOException {
        final String redirectUrl = buildRedirectUrl(path);
        getResponse().sendRedirect(redirectUrl);
    }

    private String buildRedirectUrl(String destination) {
        final String contextPath = getRequest().getContextPath();
        return getResponse().encodeRedirectURL(contextPath + destination);
    }

    protected void forward(String path) throws ServletException, IOException {
        final var requestDispatcher = getRequest().getRequestDispatcher(path);
        requestDispatcher.forward(getRequest(), getResponse());
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
