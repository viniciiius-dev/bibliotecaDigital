package com.viniciiiusdev.fateclivros.Exception;

public class SavedBookAlreadyExistsException extends RuntimeException{
    public SavedBookAlreadyExistsException(String message){
        super(message);
    }
}
