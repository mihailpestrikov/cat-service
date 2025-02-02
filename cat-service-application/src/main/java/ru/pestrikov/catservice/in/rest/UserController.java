package ru.pestrikov.catservice.in.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pestrikov.catservice.exception.Response;
import ru.pestrikov.catservice.resource.auth.SignUpRequest;
import ru.pestrikov.catservice.service.UserSignUpService;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserSignUpService userSignUpService;

    @PostMapping("/sign-up")
    public ResponseEntity<Response> signUp(@RequestBody SignUpRequest signUpRequest) {
        userSignUpService.signUp(signUpRequest);

        return ResponseEntity.ok(new Response("Sign up successful"));
    }
}
