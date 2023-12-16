package com.ohz.unscramble.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UnscrambledWordsResponse {
    String message;
    List<String> oneLetterWords = new ArrayList<>();
    List<String> twoLetterWords = new ArrayList<>();
    List<String> threeLetterWords = new ArrayList<>();
    List<String> fourLetterWords = new ArrayList<>();
    List<String> fiveLetterWords = new ArrayList<>();
    List<String> sixLetterWords = new ArrayList<>();
    List<String> sevenLetterWords = new ArrayList<>();
    List<String> eightLetterWords = new ArrayList<>();
    List<String> nineLetterWords = new ArrayList<>();
    List<String> tenLetterWords = new ArrayList<>();
    List<String> elevenLetterWords = new ArrayList<>();
    List<String> twelveLetterWords = new ArrayList<>();
    List<String> thirteenLetterWords = new ArrayList<>();
    List<String> fourteenLetterWords = new ArrayList<>();
    List<String> fifteenLetterWords = new ArrayList<>();
}
