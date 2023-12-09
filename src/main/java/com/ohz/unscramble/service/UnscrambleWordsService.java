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

    Set<String> words = getListOfWords();

    public UnscrambledWordsResponse unscrambleWords(String request) {
        UnscrambledWordsResponse response = initializeResponse();
        int totalWordsUnscrambled = 0;

        Set<String> wordList = getWordsWithUpToCharacters(words, request.length());
        for (String word : wordList) {
            if (isWordFromCharacters(word.toLowerCase(), request.toLowerCase())) {
                word = StringUtils.capitalize(word);
                totalWordsUnscrambled = totalWordsUnscrambled + 1;
                switch (word.length()) {
                    case 1:
                        response.getOneLetterWords().add(word);
                        break;
                    case 2:
                        response.getTwoLetterWOrds().add(word);
                        break;
                    case 3:
                        response.getThreeLetterWords().add(word);
                        break;
                    case 4:
                        response.getFourLetterWords().add(word);
                        break;
                    case 5:
                        response.getFiveLetterWords().add(word);
                        break;
                    case 6:
                        response.getSixLetterWords().add(word);
                        break;
                    case 7:
                        response.getSevenLetterWords().add(word);
                        break;
                    case 8:
                        response.getEightLetterWords().add(word);
                        break;
                    case 9:
                        response.getNineLetterWords().add(word);
                        break;
                    case 10:
                        response.getTenLetterWords().add(word);
                        break;
                    case 11:
                        response.getElevenLetterWords().add(word);
                        break;
                    case 12:
                        response.getTwelveLetterWords().add(word);
                        break;
                    case 13:
                        response.getThirteenLetterWords().add(word);
                        break;
                    case 14:
                        response.getFourteenLetterWords().add(word);
                        break;
                    case 15:
                        response.getFifteenLetterWords().add(word);
                        break;
                }
            }
        }

        response.setMessage(String.format("Total words unscrambled from '%s': %s", request, totalWordsUnscrambled));

        return response;
    }

    private UnscrambledWordsResponse initializeResponse() {
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        response.setOneLetterWords(new ArrayList<>());
        response.setTwoLetterWOrds(new ArrayList<>());
        response.setThreeLetterWords(new ArrayList<>());
        response.setFourLetterWords(new ArrayList<>());
        response.setFiveLetterWords(new ArrayList<>());
        response.setSixLetterWords(new ArrayList<>());
        response.setSevenLetterWords(new ArrayList<>());
        response.setEightLetterWords(new ArrayList<>());
        response.setNineLetterWords(new ArrayList<>());
        response.setTenLetterWords(new ArrayList<>());
        response.setElevenLetterWords(new ArrayList<>());
        response.setTwelveLetterWords(new ArrayList<>());
        response.setThirteenLetterWords(new ArrayList<>());
        response.setFourteenLetterWords(new ArrayList<>());
        response.setFifteenLetterWords(new ArrayList<>());

        return response;
    }

    private static Set<String> getListOfWords() {
        Set<String> listOfWords = new HashSet<>();
        try {
            File file = new File("src/main/resources/files/words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listOfWords.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }
        return listOfWords;
    }

    private static Set<String> getListOfWords7CharactersOrLess() {
        Set<String> listOfWords = new HashSet<>();
        try {
            File file = new File("src/main/resources/files/words.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listOfWords.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println(e.getCause().getMessage());
        }

        listOfWords.removeIf(word -> word.length() > 7);
        return listOfWords;
    }

    private static Set<String> getWordsWithUpToCharacters(Set<String> completeWordList, int maxCharacters){
        Set<String> wordList = completeWordList;
        wordList.removeIf(word -> word.length() > maxCharacters);
        return wordList;
    }

    private boolean isWordFromCharacters(String word, String scrambledCharacters) {
        boolean isWord = false;
        String wordToValidate = word;
        int blankLettersCount = StringUtils.countOccurrencesOf(scrambledCharacters, "*");

        scrambledCharacters = scrambledCharacters.replaceAll("\\*","");
        for (char character : scrambledCharacters.toCharArray()) {
            wordToValidate = wordToValidate.replaceFirst(String.valueOf(character), "");

            if (wordToValidate.isEmpty()) {
                isWord = true;
                break;
            }
        }

        if(wordToValidate.length() <= blankLettersCount){
            isWord = true;
        }

        return isWord;
    }
}
