package com.viniciiiusdev.fateclivros.Exception;

public class BookIsNotAvailableException extends RuntimeException {
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
