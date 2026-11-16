package com.viniciiiusdev.fateclivros.Exception;

public class BookIsNotSavedException extends RuntimeException {
    public BookIsNotSavedException(String message) {
        super(message);
    }
}
