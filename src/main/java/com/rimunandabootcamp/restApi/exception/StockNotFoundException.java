package com.rimunandabootcamp.restApi.exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String message){
        super(message);
    }
}
