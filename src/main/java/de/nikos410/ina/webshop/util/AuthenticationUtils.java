package de.nikos410.ina.webshop.util;

import de.nikos410.ina.webshop.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

import static de.nikos410.ina.webshop.controller.helper.ControllerHelper.AUTHENTICATED_USER_ATTRIBUTE_NAME;
import static java.util.Objects.isNull;

public final class AuthenticationUtils {

    // Private constructor to hide the implicit public one.
    private AuthenticationUtils() {}

    public static Optional<User> getAuthenticatedUser(HttpServletRequest httpServletRequest) {
        final Object authenticatedUser = httpServletRequest.getSession().getAttribute(AUTHENTICATED_USER_ATTRIBUTE_NAME);
        if (isNull(authenticatedUser)) {
            return Optional.empty();
        } else {
            return Optional.of((User) authenticatedUser);
        }
    }
}
