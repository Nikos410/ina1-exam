package de.nikos410.ina.webshop.controller.helper;

import de.nikos410.ina.webshop.model.entity.User;
import de.nikos410.ina.webshop.repository.InMemoryUserRepository;
import de.nikos410.ina.webshop.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static de.nikos410.ina.webshop.util.AuthenticationUtils.getAuthenticatedUser;

public class DeleteAccountControllerHelper extends ControllerHelper {

    private final UserRepository  userRepository = InMemoryUserRepository.getInstance();

    public DeleteAccountControllerHelper(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doGet() throws ServletException, IOException {
        requireSessionAuthenticated();

        forward("/de/nikos410/ina/webshop/delete-account.html");
    }

    @Override
    public void doPost() throws IOException {
        requireSessionAuthenticated();

        doDeleteAccount();
        redirect("/login");
    }

    private void doDeleteAccount() {
        final User authenticatedUser = getAuthenticatedUser(getRequest())
                .orElseThrow(() -> new IllegalStateException("No authenticated user."));
        userRepository.remove(authenticatedUser.getId());
    }
}
