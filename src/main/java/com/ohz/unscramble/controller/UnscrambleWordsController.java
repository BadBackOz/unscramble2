package com.ohz.unscramble.controller;

import com.ohz.unscramble.exception.UnscrambledWordsException;
import com.ohz.unscramble.model.UnscrambledWordsResponse;
import com.ohz.unscramble.service.UnscrambleWordsService;
import com.ohz.unscramble.validator.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UnscrambleWordsController {

    @Autowired
    UnscrambleWordsService service;

    @Autowired
    RequestValidator requestValidator;

    @GetMapping(path = "/getWords/{scrambledCharacters}")
    public ResponseEntity<UnscrambledWordsResponse> getWords (@PathVariable(value="scrambledCharacters")String scrambledCharacters){
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        try{
            requestValidator.validateUnscrambleWordsRequest(scrambledCharacters);
            response = service.unscrambleWords(scrambledCharacters);
        }catch (UnscrambledWordsException e){
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, e.getHttpStatus());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
