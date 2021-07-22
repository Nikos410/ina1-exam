package de.nikos410.ina.webshop.util;

import de.nikos410.ina.webshop.exception.BadCredentialsException;
import de.nikos410.ina.webshop.model.LoginRequest;
import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.repository.InMemoryUserRepository;
import de.nikos410.ina.webshop.repository.UserRepository;
import de.nikos410.ina.webshop.security.PasswordEncoder;
import de.nikos410.ina.webshop.security.Sha256PasswordEncoder;
import jakarta.servlet.http.HttpServletRequest;

public final class AuthenticationUtils {

    public static final String USERNAME_PARAMETER_NAME = "username";
    public static final String PASSWORD_PARAMETER_NAME = "password";

    private static final String AUTHENTICATED_USER_ATTRIBUTE_NAME = "authenticatedUser";

    private static final UserRepository USER_REPOSITORY = InMemoryUserRepository.getInstance();
    private static final PasswordEncoder PASSWORD_ENCODER = new Sha256PasswordEncoder();

    // Private constructor to hide the implicit public one.
    private AuthenticationUtils() {
    }

    public static void authenticate(HttpServletRequest httpServletRequest) throws BadCredentialsException {

        final LoginRequest loginRequest = getLoginRequestFromHttpServletRequest(httpServletRequest);
        final User user = USER_REPOSITORY.findOneByUsername(loginRequest.username())
                .filter(u -> PASSWORD_ENCODER.matches(loginRequest.password(), u.getPasswordHash()))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials."));

        assignToSession(user, httpServletRequest);
    }

    private static LoginRequest getLoginRequestFromHttpServletRequest(HttpServletRequest httpServletRequest) {
        final String username = httpServletRequest.getParameter(USERNAME_PARAMETER_NAME);
        final String password = httpServletRequest.getParameter(PASSWORD_PARAMETER_NAME);
        return new LoginRequest(username, password);
    }

    private static void assignToSession(User authenticatedUser, HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().setAttribute(AUTHENTICATED_USER_ATTRIBUTE_NAME, authenticatedUser);
    }
}
