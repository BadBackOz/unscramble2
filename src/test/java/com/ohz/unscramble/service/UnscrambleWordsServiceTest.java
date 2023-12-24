package com.ohz.unscramble.service;

import com.ohz.unscramble.model.UnscrambledWordsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

class UnscrambleWordsServiceTest {

    @InjectMocks
    UnscrambleWordsService service;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenCharacters_WhenIsWordFromCharactersTrue_ThenReturnTrue(){
        boolean isWord = Boolean.TRUE.equals(ReflectionTestUtils.invokeMethod(service, "isWordFromCharacters", "weed", "azedbwe"));

        Assertions.assertTrue(isWord);
    }

    @Test
    void givenCharacters_WhenUnscrambleWords_ThenReturnUnscrambledWords(){
        UnscrambledWordsResponse response = service.unscrambleWords("azedbwe");

        Assertions.assertNotNull(response);
    }

    @Test
    void givenAllCharacters_WhenUnscrambleWords_ThenReturnUnscrambledWords(){
        UnscrambledWordsResponse response = service.unscrambleWords("abcdefghijklmnopqrstuvwqyzabcdefghijklmnopqrstuvwqyz");

        Assertions.assertTrue(response.getOneLetterWords().contains("A"));
        Assertions.assertTrue(response.getTwoLetterWords().contains("Ab"));
        Assertions.assertTrue(response.getThreeLetterWords().contains("Low"));
        Assertions.assertTrue(response.getFourLetterWords().contains("Gets"));
        Assertions.assertTrue(response.getFiveLetterWords().contains("Rails"));
        Assertions.assertTrue(response.getSixLetterWords().contains("Groins"));
        Assertions.assertTrue(response.getSevenLetterWords().contains("Dryness"));
        Assertions.assertTrue(response.getEightLetterWords().contains("Blunders"));
        Assertions.assertTrue(response.getNineLetterWords().contains("Turquoise"));
        Assertions.assertTrue(response.getTenLetterWords().contains("Solidarity"));
        Assertions.assertTrue(response.getElevenLetterWords().contains("Enfranchise"));
        Assertions.assertTrue(response.getTwelveLetterWords().contains("Effortlessly"));
        Assertions.assertTrue(response.getThirteenLetterWords().contains("Transcribable"));
        Assertions.assertTrue(response.getFourteenLetterWords().contains("Unbreakability"));
        Assertions.assertTrue(response.getFifteenLetterWords().contains("Decompensations"));
    }

    @Test
    void givenWordList_WhenGetMapOfWordsBasedOnCharacterCount_ThenReturnWordList(){
        Map<String, List<String>> wordMap = ReflectionTestUtils.invokeMethod(service, "getMapOfWordsBasedOnCharacterCount");

        assert wordMap != null;
        List<String> oneCharacterWordList = wordMap.get("1");

        Assertions.assertTrue(oneCharacterWordList.contains("a"));
        Assertions.assertEquals(26, oneCharacterWordList.size());
    }

}
