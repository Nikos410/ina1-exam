package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.RegisterRequest;
import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.repository.InMemoryUserRepository;
import de.nikos410.ina.webshop.repository.UserRepository;
import de.nikos410.ina.webshop.security.PasswordEncoder;
import de.nikos410.ina.webshop.security.Sha256PasswordEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterControllerHelper extends ControllerHelper {

    public static final String USERNAME_PARAMETER_NAME = "username";
    public static final String PASSWORD_PARAMETER_NAME = "password";
    public static final String FULL_NAME_PARAMETER_NAME = "full-name";
    public static final String EMAIL_ADDRESS_PARAMETER_NAME = "email-address";

    private final UserRepository  userRepository = InMemoryUserRepository.getInstance();
    private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();

    public RegisterControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        forward("/de/nikos410/ina/webshop/register.jsp");
    }

    @Override
    public void doPost() throws IOException {
        final RegisterRequest registerRequest = getRegisterRequest();
        if (isNewUser(registerRequest)) {
            doRegister(registerRequest);
            redirect("/login");
        } else {
            redirect("/register?error");
        }
    }

    private RegisterRequest getRegisterRequest() {
        final HttpServletRequest request = getRequest();

        final String username = request.getParameter(USERNAME_PARAMETER_NAME);
        final String password = request.getParameter(PASSWORD_PARAMETER_NAME);
        final String fullName = request.getParameter(FULL_NAME_PARAMETER_NAME);
        final String emailAddress = request.getParameter(EMAIL_ADDRESS_PARAMETER_NAME);

        return new RegisterRequest(username, password, fullName, emailAddress);
    }

    private boolean isNewUser(RegisterRequest registerRequest) {
        return userRepository.findOneByUsername(registerRequest.username()).isEmpty();
    }

    private void doRegister(RegisterRequest registerRequest) {
        final User user = buildUser(registerRequest);
        userRepository.add(user);
    }

    private User buildUser(RegisterRequest registerRequest) {
        final var user = new User();
        user.setUsername(registerRequest.username());
        user.setPasswordHash(passwordEncoder.encode(registerRequest.password()));
        user.setFullName(registerRequest.fullName());
        user.setEmailAddress(registerRequest.emailAddress());

        return user;
    }
}
