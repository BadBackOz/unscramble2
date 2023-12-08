package com.ohz.unscramble.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UnscrambledWordsResponse {

    String message;

    List<String> oneLetterWords;
    List<String> twoLetterWOrds;
    List<String> threeLetterWords;
    List<String> fourLetterWords;
    List<String> fiveLetterWords;
    List<String> sixLetterWords;
    List<String> sevenLetterWords;

}
