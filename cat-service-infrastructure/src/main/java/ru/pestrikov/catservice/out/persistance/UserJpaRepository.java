package ru.pestrikov.catservice.out.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pestrikov.catservice.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findByOwnerId(UUID ownerId);
}
