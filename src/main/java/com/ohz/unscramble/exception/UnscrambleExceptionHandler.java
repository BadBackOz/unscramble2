package com.ohz.unscramble.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ohz.unscramble.model.UnscrambledWordsResponse;
import com.ohz.unscramble.util.EmptyListToNullFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UnscrambleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UnscrambledWordsException.class)
    public ResponseEntity<Object> handleUnscrambledWordsException(UnscrambledWordsException e){
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        response.setMessage(e.getMessage());

        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(EmptyListToNullFactory.INSTANCE)
                .setPrettyPrinting()
                .create();
        return new ResponseEntity<>(gson.toJson(response), e.getHttpStatus());
    }


}
