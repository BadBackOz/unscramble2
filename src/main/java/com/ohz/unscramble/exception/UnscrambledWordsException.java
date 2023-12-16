package com.ohz.unscramble.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnscrambledWordsException extends Exception {

    private final String message;
    private final HttpStatus httpStatus;

    public UnscrambledWordsException(String message, HttpStatus httpStatus){
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
