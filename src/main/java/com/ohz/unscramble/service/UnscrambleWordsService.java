package com.ohz.unscramble.service;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UnscrambleWordsService {

    private static final String WORD_LIST_FILE_RELATIVE_PATH = "src/main/resources/files/words.txt";

    private final Map<String, List<String>> wordListMap = getMapOfWordsBasedOnCharacterCount();

    private UnscrambledWordsResponse unscrambledWordsResponse;

    private final AtomicInteger totalCountOfWordsUnscrambled = new AtomicInteger(0);


    public UnscrambledWordsResponse unscrambleWords(String request) {
        unscrambledWordsResponse = new UnscrambledWordsResponse();
        totalCountOfWordsUnscrambled.set(0);
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 1; i < 16; i++) {
                int finalI = i;
                executorService.submit(() -> getUnscrambledWordsWithCharacterCount(finalI, request));
            }
        }

        unscrambledWordsResponse.setMessage(String.format("Total words unscrambled from '%s': %s", request, totalCountOfWordsUnscrambled));

        return unscrambledWordsResponse;
    }

    private void getUnscrambledWordsWithCharacterCount(int characterCount, String request) {
        List<String> wordsOfSpecificCharacterCount = wordListMap.get(String.valueOf(characterCount));
        int wordsUnscrambled = 0;
        if (null != wordsOfSpecificCharacterCount) {
            for (String word : wordsOfSpecificCharacterCount) {
                if (isWordFromCharacters(word.toLowerCase(), request.toLowerCase())) {
                    wordsUnscrambled = wordsUnscrambled + 1;
                    word = StringUtils.capitalize(word);
                    switch (word.length()) {
                        case 1:
                            unscrambledWordsResponse.getOneLetterWords().add(word);
                            break;
                        case 2:
                            unscrambledWordsResponse.getTwoLetterWords().add(word);
                            break;
                        case 3:
                            unscrambledWordsResponse.getThreeLetterWords().add(word);
                            break;
                        case 4:
                            unscrambledWordsResponse.getFourLetterWords().add(word);
                            break;
                        case 5:
                            unscrambledWordsResponse.getFiveLetterWords().add(word);
                            break;
                        case 6:
                            unscrambledWordsResponse.getSixLetterWords().add(word);
                            break;
                        case 7:
                            unscrambledWordsResponse.getSevenLetterWords().add(word);
                            break;
                        case 8:
                            unscrambledWordsResponse.getEightLetterWords().add(word);
                            break;
                        case 9:
                            unscrambledWordsResponse.getNineLetterWords().add(word);
                            break;
                        case 10:
                            unscrambledWordsResponse.getTenLetterWords().add(word);
                            break;
                        case 11:
                            unscrambledWordsResponse.getElevenLetterWords().add(word);
                            break;
                        case 12:
                            unscrambledWordsResponse.getTwelveLetterWords().add(word);
                            break;
                        case 13:
                            unscrambledWordsResponse.getThirteenLetterWords().add(word);
                            break;
                        case 14:
                            unscrambledWordsResponse.getFourteenLetterWords().add(word);
                            break;
                        case 15:
                            unscrambledWordsResponse.getFifteenLetterWords().add(word);
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        totalCountOfWordsUnscrambled.getAndAccumulate(wordsUnscrambled, Integer::sum);
    }

    private Set<String> getListOfWords() {
        Set<String> listOfWords = new HashSet<>();
        try {
            File file = new File(WORD_LIST_FILE_RELATIVE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                listOfWords.add(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException e) {
            System.out.printf("ERROR: WORD LIST NOT LOADED. Exception: %s%n", e);
        }
        return listOfWords;
    }

    private Map<String, List<String>> getMapOfWordsBasedOnCharacterCount() {
        Map<String, List<String>> mapOfWords = new HashMap<>();
        Set<String> allWords = getListOfWords();

        for (String word : allWords) {
            int wordLength = word.length();

            switch (wordLength) {
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

        scrambledCharacters = scrambledCharacters.replace("*", "");
        for (char character : scrambledCharacters.toCharArray()) {
            wordToValidate = wordToValidate.replaceFirst(String.valueOf(character), "");

            if (wordToValidate.isEmpty()) {
                isWord = true;
                break;
            }
        }

        if (wordToValidate.length() <= blankLettersCount) {
            isWord = true;
        }

        return isWord;
    }
}
