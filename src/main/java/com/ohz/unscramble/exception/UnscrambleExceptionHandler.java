package com.ohz.unscramble.exception;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UnscrambleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UnscrambleException.class)
    public ResponseEntity<Object> handleUnscrambledWordsException(UnscrambleException e){
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        response.setMessage(e.getMessage());

        return new ResponseEntity<>(response, e.getHttpStatus());
    }


}
