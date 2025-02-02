package ru.pestrikov.catservice.domain.model.resource;

import lombok.Getter;

@Getter
public enum CatColor {
    WHITE("White"),
    BLACK("Black"),
    GRAY("Gray"),
    BROWN("Brown"),
    ORANGE("Orange"),
    CREAM("Cream"),
    GINGER("Ginger"),
    TABBY("Tabby"),
    CALICO("Calico"),
    TORTOISE_SHELL("Tortoise Shell"),
    BLUE("Blue"),
    LILAC("Lilac"),
    CHOCOLATE("Chocolate"),
    CINNAMON("Cinnamon"),
    FAWN("Fawn"),
    SMOKE("Smoke"),
    SILVER("Silver"),
    GOLDEN("Golden");

    private final String displayName;

    CatColor(String displayName) {
        this.displayName = displayName;
    }

}