package com.ohz.unscramble.service;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Service
public class UnscrambleWordsService {

    Set<String> words = getListOfWords7CharactersOrLess();

    public UnscrambledWordsResponse unscrambleWords(String request){
        UnscrambledWordsResponse response = initializeResponse();

        if(StringUtils.hasLength(request)){
            for(String word : words){
                if(isWordFromCharacters(word, request)){
                    switch (word.length()){
                        case 1: response.getOneLetterWords().add(word);
                                break;
                        case 2: response.getTwoLetterWOrds().add(word);
                            break;
                        case 3: response.getThreeLetterWords().add(word);
                            break;
                        case 4: response.getFourLetterWords().add(word);
                            break;
                        case 5: response.getFiveLetterWords().add(word);
                            break;
                        case 6: response.getSixLetterWords().add(word);
                            break;
                        case 7: response.getSevenLetterWords().add(word);
                            break;
                    }
                }
            }
        }

        response.setMessage(String.format("Total words unscrambled from '%s': %s", request, getCountOfWordsUnscrambled(response)));

        return response;
    }

    private int getCountOfWordsUnscrambled(UnscrambledWordsResponse res){
        return res.getOneLetterWords().size() + res.getTwoLetterWOrds().size() + res.getThreeLetterWords().size() +
                res.getFourLetterWords().size() + res.getFiveLetterWords().size() + res.getSixLetterWords().size() + res.getSevenLetterWords().size();
    }

    private UnscrambledWordsResponse initializeResponse(){
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        response.setOneLetterWords(new ArrayList<>());
        response.setTwoLetterWOrds(new ArrayList<>());
        response.setThreeLetterWords(new ArrayList<>());
        response.setFourLetterWords(new ArrayList<>());
        response.setFiveLetterWords(new ArrayList<>());
        response.setSixLetterWords(new ArrayList<>());
        response.setSevenLetterWords(new ArrayList<>());

        return response;
    }

    private static Set<String> getListOfWords(){
        Set<String> listOfWords = new HashSet<>();
        try {
            File file = new File(".src/main/resources/files/words.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                listOfWords.add(scanner.nextLine());
            }
            scanner.close();
        }catch(IOException e) {
            System.out.println(e.getCause().getMessage());
        }
        return listOfWords;
    }

    private static Set<String> getListOfWords7CharactersOrLess(){
        Set<String> listOfWords = new HashSet<>();
        try {
            File file = new File("src/main/resources/files/words.txt");
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                listOfWords.add(scanner.nextLine());
            }
            scanner.close();
        }catch(IOException e) {
            System.out.println(e.getCause().getMessage());
        }

        listOfWords.removeIf(word -> word.length() > 7);
        return listOfWords;
    }

    private boolean isWordFromCharacters(String word, String scrambledCharacters){
        boolean isWord = false;
        String wordToValidate = word;
        for(char character : scrambledCharacters.toCharArray()){
            wordToValidate = wordToValidate.replaceFirst(String.valueOf(character), "");

            if(wordToValidate.isEmpty()){
                isWord = true;
                break;
            }
        }

        return isWord;
    }
}
