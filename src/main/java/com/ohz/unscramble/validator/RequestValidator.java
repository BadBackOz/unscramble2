package com.ohz.unscramble.validator;

import com.ohz.unscramble.exception.UnscrambleException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class RequestValidator {

    public void validateUnscrambleWordsRequest(String request) throws UnscrambleException {
        if(!request.matches("^[a-zA-Z*]+$")){
            throw new UnscrambleException("Invalid Request: Only Alphabetic characters and '*' allowed.",HttpStatus.BAD_REQUEST);
        }
    }
}
