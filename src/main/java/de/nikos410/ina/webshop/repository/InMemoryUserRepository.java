package de.nikos410.ina.webshop.repository;

import de.nikos410.ina.webshop.model.entity.User;

import java.util.Optional;

public class InMemoryUserRepository extends InMemoryRepository<User> implements UserRepository {

    @Override
    public Optional<User> findOneByUsername(String username) {
        return content.stream()
                .filter(u -> username.equalsIgnoreCase(u.getUsername()))
                .findFirst();
    }
}
