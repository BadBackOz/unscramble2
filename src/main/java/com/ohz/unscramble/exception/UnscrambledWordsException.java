package com.ohz.unscramble.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class UnscrambledWordsException extends Exception {

    private String message;
    private HttpStatus httpStatus;

    public UnscrambledWordsException(){

    }

    public UnscrambledWordsException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
