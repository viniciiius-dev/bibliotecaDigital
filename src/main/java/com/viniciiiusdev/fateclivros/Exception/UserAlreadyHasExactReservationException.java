package com.viniciiiusdev.fateclivros.Exception;

public class UserAlreadyHasExactReservationException extends RuntimeException {
    public UserAlreadyHasExactReservationException(String message) {
        super(message);
    }
}
