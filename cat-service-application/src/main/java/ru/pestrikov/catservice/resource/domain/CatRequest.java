package ru.pestrikov.catservice.resource.domain;

import lombok.Data;
import ru.pestrikov.catservice.domain.model.resource.CatColor;

import java.time.LocalDate;

@Data
public class CatRequest {
    private String name;
    private LocalDate birthDate;
    private CatColor color;
}

