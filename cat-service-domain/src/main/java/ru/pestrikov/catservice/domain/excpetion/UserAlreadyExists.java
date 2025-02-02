package ru.pestrikov.catservice.domain.excpetion;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(String message) { super(message); }
}
