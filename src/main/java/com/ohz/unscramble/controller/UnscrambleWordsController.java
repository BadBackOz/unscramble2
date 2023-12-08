package com.ohz.unscramble.controller;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import com.ohz.unscramble.service.UnscrambleWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UnscrambleWordsController {

    @Autowired
    UnscrambleWordsService service;

    @PostMapping(path = "/getWords")
    public ResponseEntity<UnscrambledWordsResponse> getWords (@RequestBody String request){
        UnscrambledWordsResponse response;

        response = service.unscrambleWords(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
