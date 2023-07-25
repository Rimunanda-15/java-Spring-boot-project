package com.rimunandabootcamp.restApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice  //untuk mmeberitahu spring untuk menghandle exception
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorDetails> idNotFoundException(IdNotFoundException ex){
        ErrorDetails errorModel = new ErrorDetails(false, ex.getMessage());
        return  new ResponseEntity<ErrorDetails>(errorModel, HttpStatus.NOT_FOUND);
    }
}
