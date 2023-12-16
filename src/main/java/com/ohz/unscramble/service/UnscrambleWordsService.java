package com.ohz.unscramble.service;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class UnscrambleWordsService {

    Map<String, List<String>> wordListMap = getMapOfWordsBasedOnCharacterCount();

    public UnscrambledWordsResponse unscrambleWords(String request) {
        UnscrambledWordsResponse response = new UnscrambledWordsResponse();
        int totalWordsUnscrambled = 0;

        for(int i=1; i<request.length()+1; i++){
            List<String> wordsOfSpecificCharacterCount = wordListMap.get(String.valueOf(i));
            if(null == wordsOfSpecificCharacterCount){
                continue;
            }
            for (String word : wordsOfSpecificCharacterCount) {
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
                        default:
                            break;
                    }
                }
            }
        }

        response.setMessage(String.format("Total words unscrambled from '%s': %s", request, totalWordsUnscrambled));

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

    private static Map<String, List<String>> getMapOfWordsBasedOnCharacterCount(){
        Map<String, List<String>> mapOfWords = new HashMap<>();
        Set<String> allWords = getListOfWords();

        for(String word : allWords){
            int wordLength = word.length();

            switch (wordLength){
                case 1:
                    mapOfWords.computeIfAbsent("1", k -> new ArrayList<>()).add(word);
                    break;
                case 2:
                    mapOfWords.computeIfAbsent("2", k -> new ArrayList<>()).add(word);
                    break;
                case 3:
                    mapOfWords.computeIfAbsent("3", k -> new ArrayList<>()).add(word);
                    break;
                case 4:
                    mapOfWords.computeIfAbsent("4", k -> new ArrayList<>()).add(word);
                    break;
                case 5:
                    mapOfWords.computeIfAbsent("5", k -> new ArrayList<>()).add(word);
                    break;
                case 6:
                    mapOfWords.computeIfAbsent("6", k -> new ArrayList<>()).add(word);
                    break;
                case 7:
                    mapOfWords.computeIfAbsent("7", k -> new ArrayList<>()).add(word);
                    break;
                case 8:
                    mapOfWords.computeIfAbsent("8", k -> new ArrayList<>()).add(word);
                    break;
                case 9:
                    mapOfWords.computeIfAbsent("9", k -> new ArrayList<>()).add(word);
                    break;
                case 10:
                    mapOfWords.computeIfAbsent("10", k -> new ArrayList<>()).add(word);
                    break;
                case 11:
                    mapOfWords.computeIfAbsent("11", k -> new ArrayList<>()).add(word);
                    break;
                case 12:
                    mapOfWords.computeIfAbsent("12", k -> new ArrayList<>()).add(word);
                    break;
                case 13:
                    mapOfWords.computeIfAbsent("13", k -> new ArrayList<>()).add(word);
                    break;
                case 14:
                    mapOfWords.computeIfAbsent("14", k -> new ArrayList<>()).add(word);
                    break;
                case 15:
                    mapOfWords.computeIfAbsent("15", k -> new ArrayList<>()).add(word);
                    break;
                default:
                    break;
            }
        }
        return mapOfWords;
    }

    private boolean isWordFromCharacters(String word, String scrambledCharacters) {
        boolean isWord = false;
        String wordToValidate = word;
        int blankLettersCount = StringUtils.countOccurrencesOf(scrambledCharacters, "*");

        scrambledCharacters = scrambledCharacters.replace("*","");
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
