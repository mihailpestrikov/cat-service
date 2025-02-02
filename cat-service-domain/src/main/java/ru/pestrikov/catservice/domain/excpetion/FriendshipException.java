package ru.pestrikov.catservice.domain.excpetion;

public class FriendshipException extends RuntimeException {
    public FriendshipException(String message) {
        super(message);
    }
}
