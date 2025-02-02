package ru.pestrikov.catservice.domain.excpetion;

public class OwnershipException extends RuntimeException {
    public OwnershipException(String message) {
        super(message);
    }
}
