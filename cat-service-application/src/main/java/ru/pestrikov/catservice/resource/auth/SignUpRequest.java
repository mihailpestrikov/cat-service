package ru.pestrikov.catservice.resource.auth;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String name;
    private LocalDate birthDate;
}
