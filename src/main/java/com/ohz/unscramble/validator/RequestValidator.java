package com.ohz.unscramble.validator;

import com.ohz.unscramble.exception.UnscrambledWordsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validateUnscrambleWordsRequest(String request) throws UnscrambledWordsException {
        if(!request.matches("^[a-zA-Z*]+$")){
            throw new UnscrambledWordsException("Invalid Request: Only Alphabetic characters and '*' allowed.",HttpStatus.BAD_REQUEST);
        }
    }
}
