package ru.pestrikov.catservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pestrikov.catservice.domain.in.application.UserService;
import ru.pestrikov.catservice.domain.model.User;
import ru.pestrikov.catservice.resource.auth.UserDetailsImpl;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userService.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new UserDetailsImpl(userOptional.get());
    }
}
