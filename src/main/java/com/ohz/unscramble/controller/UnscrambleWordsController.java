package com.ohz.unscramble.controller;

import com.ohz.unscramble.exception.UnscrambleException;
import com.ohz.unscramble.model.UnscrambledWordsResponse;
import com.ohz.unscramble.service.UnscrambleWordsService;
import com.ohz.unscramble.validator.RequestValidator;
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
        this.requestValidator = new RequestValidator();
    }

    UnscrambleWordsService service;

    RequestValidator requestValidator;

    @GetMapping(path = "/getWords/{scrambledCharacters}")
    public ResponseEntity<Object> getWords(@PathVariable(value = "scrambledCharacters") String scrambledCharacters) throws UnscrambleException {
        UnscrambledWordsResponse response;
        requestValidator.validateUnscrambleWordsRequest(scrambledCharacters);
        response = service.unscrambleWords(scrambledCharacters);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
