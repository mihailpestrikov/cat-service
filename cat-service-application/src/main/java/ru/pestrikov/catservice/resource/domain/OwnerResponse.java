package ru.pestrikov.catservice.resource.domain;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class OwnerResponse {
    private UUID id;
    private String name;
    private LocalDate birthDate;
}
