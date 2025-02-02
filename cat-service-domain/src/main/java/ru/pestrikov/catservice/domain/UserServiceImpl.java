package ru.pestrikov.catservice.domain;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ru.pestrikov.catservice.domain.architecture.DomainService;
import ru.pestrikov.catservice.domain.excpetion.UserAlreadyExists;
import ru.pestrikov.catservice.domain.in.application.UserService;
import ru.pestrikov.catservice.domain.model.User;
import ru.pestrikov.catservice.domain.out.infrastructure.UserRepository;

import java.util.Optional;
import java.util.UUID;

@DomainService
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User create(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserAlreadyExists("User with username " + user.getUsername() + " already exists");
        }

        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User object) {
        var userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }

        var user = userOptional.get();

        return userRepository.save(user.update(object));
    }

    @Override
    public User deletedById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(id);
            return userOptional.get();
        } else {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }
    }

    @Override
    public User findById(UUID id) {
        var userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with id " + id + " not found");
        }

        return userOptional.get();
    }
}
