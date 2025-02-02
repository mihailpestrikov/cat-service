package ru.pestrikov.catservice.resource.domain;

import lombok.Data;
import ru.pestrikov.catservice.domain.model.resource.CatColor;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CatResponse {
    private UUID id;
    private String name;
    private LocalDate birthDate;
    private CatColor color;
    private String ownerName;
}
