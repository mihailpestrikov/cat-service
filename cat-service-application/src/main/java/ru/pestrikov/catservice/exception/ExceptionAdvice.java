package ru.pestrikov.catservice.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pestrikov.catservice.domain.excpetion.FriendshipException;
import ru.pestrikov.catservice.domain.excpetion.OwnershipException;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Response> handleException(EntityNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({FriendshipException.class})
    public ResponseEntity<Response> handleException(FriendshipException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OwnershipException.class})
    public ResponseEntity<Response> handleException(OwnershipException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response> handleAccessDeniedException(AccessDeniedException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
