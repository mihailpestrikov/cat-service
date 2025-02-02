package ru.pestrikov.catservice.out.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pestrikov.catservice.domain.model.User;
import ru.pestrikov.catservice.domain.out.infrastructure.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> findByOwnerId(UUID ownerId) {
        return userJpaRepository.findByOwnerId(ownerId);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userJpaRepository.findById(id);
    }
}
