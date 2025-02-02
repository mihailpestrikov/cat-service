package ru.pestrikov.catservice.domain.out.infrastructure;

import ru.pestrikov.catservice.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    Optional<User> findByOwnerId(UUID ownerId);
}
