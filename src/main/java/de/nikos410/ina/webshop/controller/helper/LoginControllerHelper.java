package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.exception.BadCredentialsException;
import de.nikos410.ina.webshop.model.LoginRequest;
import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.repository.InMemoryUserRepository;
import de.nikos410.ina.webshop.repository.UserRepository;
import de.nikos410.ina.webshop.security.PasswordEncoder;
import de.nikos410.ina.webshop.security.Sha256PasswordEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginControllerHelper extends ControllerHelper {

    public static final String USERNAME_PARAMETER_NAME = "username";
    public static final String PASSWORD_PARAMETER_NAME = "password";

    private final UserRepository  userRepository = InMemoryUserRepository.getInstance();
    private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();

    public LoginControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        forward("/de/nikos410/ina/webshop/login.jsp");
    }

    @Override
    public void doPost() throws IOException {

        try {
            doLogin();
            redirect("/shop");
        } catch (BadCredentialsException e) {
            redirect("/login?error");
        }
    }

    private void doLogin() throws BadCredentialsException {
        final LoginRequest loginRequest = getLoginRequest();
        final User user = userRepository.findOneByUsername(loginRequest.username())
                .filter(u -> passwordEncoder.matches(loginRequest.password(), u.getPasswordHash()))
                .orElseThrow(() -> new BadCredentialsException("Bad credentials."));

        assignToSession(user);
    }

    private LoginRequest getLoginRequest() {
        final HttpServletRequest request = getRequest();
        final String username = request.getParameter(USERNAME_PARAMETER_NAME);
        final String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        return new LoginRequest(username, password);
    }

    private void assignToSession(User authenticatedUser) {
        setSessionAttribute(AUTHENTICATED_USER_ATTRIBUTE_NAME, authenticatedUser);
    }
}
