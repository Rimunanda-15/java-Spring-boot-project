package com.rimunandabootcamp.restApi.exception;

public class IdNotFoundException extends  RuntimeException {
    public IdNotFoundException(String message) {
        super(message);
    }
}
