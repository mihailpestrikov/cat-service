package ru.pestrikov.catservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.pestrikov.catservice.domain.in.application.OwnerService;
import ru.pestrikov.catservice.domain.in.application.UserService;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.domain.model.User;
import ru.pestrikov.catservice.resource.auth.SignUpRequest;

@Service
@RequiredArgsConstructor
public class UserSignUpService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final OwnerService ownerService;

    public void signUp(SignUpRequest signUpRequest) {
        var owner = new Owner();
        owner.setBirthDate(signUpRequest.getBirthDate());
        owner.setName(signUpRequest.getName());

        owner = ownerService.create(owner);

        var user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setOwner(owner);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userService.create(user);
    }
}
