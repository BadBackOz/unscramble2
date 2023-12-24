package com.ohz.unscramble.controller;

import com.ohz.unscramble.exception.UnscrambleException;
import com.ohz.unscramble.model.UnscrambledWordsResponse;
import com.ohz.unscramble.service.UnscrambleWordsService;
import com.ohz.unscramble.requestvalidator.GetWordsRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Controller
@RestControllerAdvice
public class UnscrambleWordsController {

    private UnscrambleWordsController() {
        this.service = new UnscrambleWordsService();
        this.getWordsRequestValidator = new GetWordsRequestValidator();
    }

    UnscrambleWordsService service;

    GetWordsRequestValidator getWordsRequestValidator;

    @GetMapping(path = "/getWords/{scrambledCharacters}")
    public ResponseEntity<Object> getWords(@PathVariable(value = "scrambledCharacters") String scrambledCharacters) throws UnscrambleException {
        UnscrambledWordsResponse response;
        getWordsRequestValidator.validateUnscrambleWordsRequest(scrambledCharacters);
        response = service.unscrambleWords(scrambledCharacters);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
