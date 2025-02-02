package ru.pestrikov.catservice.domain.in.application;

import ru.pestrikov.catservice.domain.model.User;

import java.util.Optional;

public interface UserService extends BaseService<User> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
