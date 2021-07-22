package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.User;

import java.util.Optional;

public interface UserRepository extends Repository<User> {

    /**
     * Finds a specific user by it's username.
     *
     * @param username The username of the user to find.
     * @return An optional containing the user with the specified username.
     * An empty optional if no user with this username is known.
     */
    Optional<User> findOneByUsername(String username);
}
