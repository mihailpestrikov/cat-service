package ru.pestrikov.catservice.CustomUserSetup;

import ru.pestrikov.catservice.domain.model.Cat;
import ru.pestrikov.catservice.domain.model.Owner;
import ru.pestrikov.catservice.domain.model.resource.CatColor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class CatOwnerSetup {
    public static final Owner owner = new Owner(
            UUID.fromString("4e40147f-25d2-4bb2-bf52-25563ec8522d"),
            "testOwner",
            LocalDate.of(2000, 1, 29),
            new ArrayList<>() {
            });

    public static final Cat cat = new Cat(
            UUID.fromString("4e40147f-25d2-4bb2-bf52-25563ec8522d"),
            "leftCat",
            LocalDate.of(2024, 1, 10),
            CatColor.WHITE,
            owner,
            new ArrayList<>() {
            });
}
