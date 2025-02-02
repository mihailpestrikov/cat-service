package ru.pestrikov.catservice.resource.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OwnerRequest {
    private String name;
    private LocalDate birthDate;
}
